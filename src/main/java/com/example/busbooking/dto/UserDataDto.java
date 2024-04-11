package com.example.busbooking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDataDto {

    private String username;

    private String surname;

    private String patronymic;

    private Integer serialPassport;

    private Integer numberPassport;
}
