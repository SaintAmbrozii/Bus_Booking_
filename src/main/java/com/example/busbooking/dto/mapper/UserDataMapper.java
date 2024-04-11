package com.example.busbooking.dto.mapper;

import com.example.busbooking.domain.Ticket;
import com.example.busbooking.dto.UserDataDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDataMapper extends Mappable<Ticket, UserDataDto>{

}
