package com.example.busbooking.service;

import com.example.busbooking.domain.*;
import com.example.busbooking.dto.BookingDto;
import com.example.busbooking.dto.UserDataDto;
import com.example.busbooking.dto.mapper.UserDataMapper;
import com.example.busbooking.exception.BookingException;
import com.example.busbooking.exception.NotFoundException;
import com.example.busbooking.exception.TimeOverException;
import com.example.busbooking.repository.BookingRepo;
import com.example.busbooking.repository.BusRepo;
import com.example.busbooking.repository.TicketRepo;
import com.example.busbooking.repository.UserRepo;
import com.example.busbooking.security.UserPrincipal;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Service
@Slf4j
public class BookingService {

    private final UserRepo userRepo;
    private final TicketRepo ticketRepo;
    private final BusRepo busRepo;
    private final BookingRepo bookingRepo;
    private final MailService mailService;

    @Autowired
    private UserDataMapper userDataMapper;

    private static final Integer EXPIRY_MIN = 30;
    private final LocalDateTime currentDateTime = LocalDateTime.now();




    public BookingService(UserRepo userRepo, TicketRepo ticketRepo, BusRepo busRepo,
                          BookingRepo bookingRepo, MailService mailService) {
        this.userRepo = userRepo;
        this.ticketRepo = ticketRepo;
        this.busRepo = busRepo;
        this.bookingRepo = bookingRepo;
        this.mailService = mailService;
    }


    public List<Booking> findAll() {
        return bookingRepo.findAll();
    }

    public void deleteById(Long id) {
        bookingRepo.deleteById(id);
    }



    @Transactional
    public Booking booking (UserPrincipal owner, BookingDto bookingDto) {

        Optional<Bus> inDb = busRepo.findById(bookingDto.getBusId());
        Optional<User> authUser = userRepo.findById(owner.getId());
        Booking booking = new Booking();
        booking.setLockExpiry(LocalDateTime.now().plusMinutes(EXPIRY_MIN));
        if (inDb.isEmpty()) {
            throw new NotFoundException("данный рейс отсутствует");
        }
        if (authUser.isEmpty()) {
            throw new NotFoundException("данный пользователь отсутствует");
        }
        Bus currentBus = inDb.get();
        User user = authUser.get();
       Iterable<Ticket> avaible = ticketRepo.findAllById(bookingDto.getTicketIds());
       List<Ticket> bookingTiсkets = new ArrayList<>();
       Integer aveableSeats = ticketRepo.countTicketsByBusIdAndBookingIsFalse(currentBus.getId());
       if (aveableSeats<currentBus.getCapacity()) {

           for (Ticket ticket:avaible) {
                  if (ticket.getBooking().equals(true)) {
                   throw new BookingException("данный билет уже забронирован");
               }
               if (currentDateTime.isBefore(ticket.getDepTime())) {
                   ticket.setBooking(true);
                   ticket.setUserId(user.getId());
                   ticket.setUserBooking(booking);
                   bookingTiсkets.add(ticket);
                   log.info("выбраны места для бронирования");
               }else {
                   throw new TimeOverException("время выбора истекло");
               }
               ticketRepo.saveAll(bookingTiсkets);

           }

           booking.setTickets(bookingTiсkets);
           booking.setUser(user);
           booking.setBusId(currentBus.getId());
           booking.setBookingTime(LocalDateTime.now());
           log.info("места выбраны");

       }
       return bookingRepo.save(booking);

    }

    @Transactional
    public Booking addUserData(Long bookingId, UserPrincipal owner, UserDataDto userDataDto) {
        Optional<User> authUser = userRepo.findById(owner.getId());
        if (authUser.isEmpty()) {
            throw new NotFoundException("данный пользователь отсутствует");
        }
        User user = authUser.get();
        Optional<Booking> userBooking = bookingRepo.findById(bookingId);
        if (userBooking.isEmpty()) {
            throw new NotFoundException("нет данных бронирования");
        }
        Booking booking = userBooking.get();
        if (bookingRepo.existsBookingByUser(user)) {
            List<Ticket> tickets = booking.getTickets();
            for (Ticket ticket:tickets) {
                ticket.setUsername(userDataDto.getUsername());
                ticket.setSurname(userDataDto.getSurname());
                ticket.setPatronymic(userDataDto.getPatronymic());
                ticket.setSerialPassport(userDataDto.getSerialPassport());
                ticket.setNumberPassport(user.getNumberPassport());
                ticketRepo.saveAll(tickets);
            }
            booking.setTickets(tickets);
        }
        return bookingRepo.save(booking);

    }




    @Transactional
    public Boolean cancelBookingTicket (Long ticketId, UserPrincipal owner) {

        Optional<User> authUser = userRepo.findById(owner.getId());
        Optional<Ticket> ticket = ticketRepo.findById(ticketId);
        if (authUser.isEmpty()) {
            throw new NotFoundException("данный пользователь отсутствует");
        } User user = authUser.get();
        if (ticket.isEmpty()) {
            throw new NotFoundException("нет такого билета");

        }
        Ticket bookingTicket = ticket.get();
            if (currentDateTime.isBefore(bookingTicket.getDepTime().minusMinutes(5)))
            //если дата отправления в билете больше,чем на 5 мин текущего времени
            {//выставляем нулевые значения полей покупки билета при отказе
                bookingTicket.setBooking(false);
                bookingTicket.setUsername(null);
                bookingTicket.setSurname(null);
                bookingTicket.setPatronymic(null);
                bookingTicket.setSerialPassport(null);
                bookingTicket.setNumberPassport(null);

                Properties props = new Properties();
                props.setProperty("ticket.number", bookingTicket.getNumber());
                props.setProperty("ticket.seat", String.valueOf(bookingTicket.getSeat()));

                mailService.sendEmail(user, MailType.CANCEL_BOOKING,props);//майл сервис отправляет данные об отмене бронирования
                //удаляем билет из списка билетов пользователя и сохраняем;
                log.info("данные билета обнулены" + ticket);
                //удаляем из множества билетов забронированный билет и задав ему нулевые параметры и сохраняем
                ticketRepo.save(bookingTicket);
                log.info("бронирование отменено");

            }else {
                throw new TimeOverException("время отмены бронирования истекло");
            }
        return true;
    }
}
