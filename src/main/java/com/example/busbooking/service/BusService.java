package com.example.busbooking.service;

import com.example.busbooking.domain.Bus;
import com.example.busbooking.domain.Ticket;
import com.example.busbooking.exception.NotFoundException;
import com.example.busbooking.repository.BusRepo;
import com.example.busbooking.repository.TicketRepo;
import com.example.busbooking.utils.CsvHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BusService {



    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final BusRepo busRepo;
    private final TicketRepo ticketRepo;


    public BusService(BusRepo busRepo, TicketRepo ticketRepo) {
        this.busRepo = busRepo;
        this.ticketRepo = ticketRepo;
    }


    public Bus addBus(Bus bus) {
        Bus newBus = new Bus();
        newBus.setBus_number(bus.getBus_number());
        newBus.setCapacity(bus.getCapacity());
        newBus.setTolocality(bus.getTolocality());
        newBus.setFromlocality(bus.getFromlocality());
        newBus.setDep(bus.getDep());
        newBus.setArr(bus.getArr());
        newBus.setDate(bus.getDate());
        return busRepo.save(newBus);
    }

    public Bus update(Long id,Bus bus) {
        Optional<Bus> inDb = busRepo.findById(id);
        if (inDb.isEmpty()){
            throw new NotFoundException("такой маршрут отсутствует");
        }Bus newBus  = inDb.get();
        newBus.setBus_number(bus.getBus_number());
        newBus.setCapacity(bus.getCapacity());
        newBus.setDep(bus.getDep());
        newBus.setArr(bus.getArr());
        newBus.setDate(bus.getDate());
        newBus.setFromlocality(bus.getFromlocality());
        newBus.setTolocality(bus.getTolocality());
        return busRepo.save(newBus);
    }


    public Optional<Bus> findById(Long id) {
        return busRepo.findById(id);
    }

    public List<Bus> findAll() {
        return busRepo.findAll();
    }

    public List<Ticket> findAllByBus(Long id) {

        return ticketRepo.findAllByBusId(id);
    }

    public void deleteById(Long id) {
        busRepo.deleteById(id);
        log.info("автобус удален");
    }


    public void saveAllCsv(MultipartFile file) {
        CsvHelper.checkIfValidFile(file);
        try {
            List<Bus> buses = CsvHelper.csvToBuses(file.getInputStream());
            busRepo.saveAll(buses);
            log.info("файл автобусов загружен");
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }


}
