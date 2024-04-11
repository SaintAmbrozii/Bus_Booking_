package com.example.busbooking.controller;

import com.example.busbooking.domain.Ticket;
import com.example.busbooking.dto.TicketDto;
import com.example.busbooking.service.TicketService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public Ticket add(@RequestBody TicketDto ticketDto) {
        return ticketService.save(ticketDto);
    }

    @GetMapping("{id}")
    public Optional<Ticket> findById(@PathVariable(name = "id") Long id) {
        return ticketService.findById(id);
    }
    @PutMapping("{id}")
    public Ticket update(@PathVariable(name = "id") Long id, @RequestBody TicketDto ticketDto) {
        return ticketService.update(id, ticketDto);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        ticketService.delete(id);
    }
    @GetMapping
    public List<Ticket> findAll(){
        return ticketService.findAll();
    }

    @PostMapping("upload/csv")
    public void saveAllCsv(@RequestPart MultipartFile file) {
        ticketService.saveAllCsv(file);
    }
}
