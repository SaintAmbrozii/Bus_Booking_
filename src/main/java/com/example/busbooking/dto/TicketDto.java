package com.example.busbooking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class TicketDto {

    private Long id;

    private Long busId;

    private Long userId;

    private String number;

    private Integer seat;

    private Double cost;

    private Boolean booking;

    private String username;

    private String surname;

    private String patronymic;

    private Integer serialPassport;

    private Integer numberPassport;


    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm"
    )
    private LocalDateTime depTime;

    @DateTimeFormat(
            iso = DateTimeFormat.ISO.TIME
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm"
    )
    private LocalDateTime arrTime;


}
