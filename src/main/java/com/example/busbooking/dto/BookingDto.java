package com.example.busbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class BookingDto {



    private Long busId;

    private Set<Long> ticketIds;




}
