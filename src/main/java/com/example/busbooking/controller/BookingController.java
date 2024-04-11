package com.example.busbooking.controller;

import com.example.busbooking.domain.Booking;
import com.example.busbooking.dto.BookingDto;
import com.example.busbooking.dto.UserDataDto;
import com.example.busbooking.security.UserPrincipal;
import com.example.busbooking.service.BookingService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/booking")
public class BookingController {

    private final BookingService bookingService;


    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking booking(@AuthenticationPrincipal UserPrincipal user, @RequestBody BookingDto dto)  {
       return  bookingService.booking(user, dto);
    }

    @PutMapping("{id}")
    public Booking booking(@PathVariable(name = "id")Long id, @AuthenticationPrincipal UserPrincipal user,
                           @RequestBody UserDataDto userDataDto) {
        return bookingService.addUserData(id,user,userDataDto);
    }


    @GetMapping
    public List<Booking> findAll() {
        return bookingService.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable(name = "id")Long id) {
        bookingService.deleteById(id);
    }


}
