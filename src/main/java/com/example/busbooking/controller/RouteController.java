package com.example.busbooking.controller;

import com.example.busbooking.domain.Bus;
import com.example.busbooking.domain.Ticket;
import com.example.busbooking.service.RouteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public List<Bus> findByFromAndToLocalityWithDate(String from, String to, LocalDate date) {
        return routeService.findAllByFromAndToLocalityWithDate(from, to, date);
    }

    @GetMapping("{id}")
    public List<Ticket> findTicketsByBusId(@PathVariable(name = "id") Long id) {
        return routeService.findAllByBusId(id);
    }

}
