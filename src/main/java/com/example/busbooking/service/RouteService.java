package com.example.busbooking.service;

import com.example.busbooking.domain.Bus;
import com.example.busbooking.domain.Ticket;
import com.example.busbooking.repository.BusRepo;
import com.example.busbooking.repository.TicketRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RouteService {

    private final BusRepo busRepo;
    private final TicketRepo ticketRepo;

    public RouteService(BusRepo busRepo, TicketRepo ticketRepo) {
        this.busRepo = busRepo;
        this.ticketRepo = ticketRepo;
    }

    public List<Bus> findAllByFromAndToLocalityWithDate(String from, String to, LocalDate date) {
        return busRepo.findAllByFromlocalityAndTolocalityAndDate(from, to, date);
    }

    public List<Ticket> findAllByBusId(Long id) {
        return ticketRepo.findAllByBusId(id);
    }
}
