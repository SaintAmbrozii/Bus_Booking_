package com.example.busbooking.dto.mapper;

import com.example.busbooking.domain.Ticket;
import com.example.busbooking.dto.TicketDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketMapper extends Mappable<Ticket, TicketDto> {

}
