package com.example.busbooking.service;

import com.example.busbooking.domain.Ticket;
import com.example.busbooking.dto.TicketDto;
import com.example.busbooking.exception.NotFoundException;
import com.example.busbooking.repository.TicketRepo;
import com.example.busbooking.utils.CsvHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TicketService {

    private final TicketRepo ticketRepo;

    public TicketService(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    public List<Ticket> findAll() {
        return ticketRepo.findAll();
    }



    public Ticket save(TicketDto ticketDto)
    {
        Ticket newTicket = new Ticket();
        newTicket.setBusId(ticketDto.getBusId());
        newTicket.setCost(ticketDto.getCost());
        newTicket.setSeat(ticketDto.getSeat());
        newTicket.setArrTime(ticketDto.getArrTime());
        newTicket.setDepTime(ticketDto.getDepTime());
        newTicket.setBooking(false);

        return ticketRepo.save(newTicket);
    }

    public Optional<Ticket> findById(Long id) {
        return ticketRepo.findById(id);
    }

    public Ticket update(Long id, TicketDto ticket) {
        Optional<Ticket> inData = ticketRepo.findById(id);
        if (inData.isEmpty()){
           throw new NotFoundException("нет такого билета");
        }
        Ticket inDb = inData.get();
        inDb.setCost(ticket.getCost());
        inDb.setSeat(ticket.getSeat());
        inDb.setDepTime(ticket.getArrTime());
        inDb.setArrTime(ticket.getDepTime());
        inDb.setBooking(ticket.getBooking());
        return ticketRepo.save(inDb);
    }

    public void delete(Long id){
        ticketRepo.deleteById(id);
        log.info("билет удален");
    }


    public void saveAllCsv(MultipartFile file) {
        CsvHelper.checkIfValidFile(file);
        try {
            List<Ticket> tickets = CsvHelper.csvToTickets(file.getInputStream());
            ticketRepo.saveAll(tickets);
            log.info("файл билетов загружен");
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
}
