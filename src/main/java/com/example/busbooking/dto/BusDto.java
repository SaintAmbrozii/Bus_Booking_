package com.example.busbooking.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class BusDto {


    private Long id;
    private String bus_number;
    private String fromlocality;
    private String tolocality;
    private Integer capacity;
    private LocalDateTime dep;
    private LocalDateTime arr;
    private LocalDate date;

}
