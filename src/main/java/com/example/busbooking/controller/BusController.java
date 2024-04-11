package com.example.busbooking.controller;

import com.example.busbooking.domain.Bus;
import com.example.busbooking.domain.Ticket;
import com.example.busbooking.service.BusService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/buses")
public class BusController {

    private final BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    @PostMapping
    public Bus addBus(@RequestBody Bus bus) {
        return busService.addBus(bus);
    }

    @PutMapping("{id}")
    public Bus update(@PathVariable(name = "id") Long id,@RequestBody Bus bus) {
        return busService.update(id, bus);
    }
    @GetMapping("{id}")
    public Optional<Bus> findById(@PathVariable(name = "id")Long id) {
        return busService.findById(id);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        busService.deleteById(id);
    }

    @GetMapping
    public List<Bus> findAll() {
        return busService.findAll();
    }

    @GetMapping("{id}/tickets")
    public List<Ticket> findAllBuBus(@PathVariable(name = "id") Long id) {
        return busService.findAllByBus(id);
    }

    @PostMapping("upload/csv")
    public void saveAllCsv(@RequestPart MultipartFile file) {
        busService.saveAllCsv(file);
    }
}
