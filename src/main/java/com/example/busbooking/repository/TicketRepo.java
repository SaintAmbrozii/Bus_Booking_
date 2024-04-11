package com.example.busbooking.repository;

import com.example.busbooking.domain.Booking;
import com.example.busbooking.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepo extends JpaRepository<Ticket,Long> {


    boolean existsAllByBusId(Long id);

    boolean existsAllByUserId (Long id);

    List<Ticket> deleteAllByUserBooking(Booking booking);

    List<Ticket> findAllByBusId(Long id);

    List<Ticket> findAllByUserBooking(Booking booking);

    List<Ticket> findAllByUserId(Long id);

    Integer countTicketsByBusIdAndBookingIsFalse(Long id);




}
