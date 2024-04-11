package com.example.busbooking.dto.mapper;

import com.example.busbooking.domain.Bus;
import com.example.busbooking.dto.BusDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BusMapper extends Mappable<Bus, BusDto>{

}
