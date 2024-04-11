package com.example.busbooking.dto;

import com.example.busbooking.domain.AuthProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class UserDto {

    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private String password;
    private String phone;
    private String gender;
    private Integer serialPassport;
    private Integer numberPassport;
    private String givePass;
    private String locale;
    private String address;
    private AuthProvider provider;
    private String providerId;
    private Integer enabled;
}
