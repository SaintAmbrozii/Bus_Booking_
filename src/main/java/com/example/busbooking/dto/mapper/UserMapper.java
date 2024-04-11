package com.example.busbooking.dto.mapper;

import com.example.busbooking.domain.User;
import com.example.busbooking.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto>{

}
