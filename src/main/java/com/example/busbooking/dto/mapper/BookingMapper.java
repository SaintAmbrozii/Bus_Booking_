package com.example.busbooking.dto.mapper;

import com.example.busbooking.domain.Booking;
import com.example.busbooking.dto.BookingDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper extends Mappable<Booking, BookingDto> {

}
