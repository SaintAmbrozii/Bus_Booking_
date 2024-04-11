package com.example.busbooking.controller;

import com.example.busbooking.domain.User;
import com.example.busbooking.dto.UserDto;
import com.example.busbooking.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping
    public User register(@RequestBody UserDto userDto) {
        return userService.register(userDto);
    }

    @GetMapping("{id}")
    public Optional<User> findById(@PathVariable(name = "id") Long id)
    {
        return userService.findById(id);
    }

    @PutMapping("{id}")
    public User update(@PathVariable(name = "id")Long id,@RequestBody UserDto userDto){
        return userService.update(id, userDto);
    }
    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        userService.delete(id);
    }
    
}
