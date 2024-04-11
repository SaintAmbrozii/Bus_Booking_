package com.example.busbooking.repository;


import com.example.busbooking.domain.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BusRepo extends JpaRepository<Bus,Long> {


    List<Bus> findAllByFromlocalityAndTolocalityAndDate(String from, String to, LocalDate date);




}
