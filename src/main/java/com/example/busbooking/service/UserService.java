package com.example.busbooking.service;

import com.example.busbooking.domain.AuthProvider;
import com.example.busbooking.domain.MailType;
import com.example.busbooking.domain.User;
import com.example.busbooking.dto.UserDto;
import com.example.busbooking.exception.NotFoundException;
import com.example.busbooking.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Service
@Slf4j
public class UserService {

    private final UserRepo userRepo;
    private final MailService mailService;

    public UserService(UserRepo userRepo, MailService mailService) {
        this.userRepo = userRepo;
        this.mailService = mailService;
    }
    public List<User> getAll() {
        return userRepo.findAll();
    }

    public User register (UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setPassword(user.getPassword());
        user.setEnabled(1);
        user.setProvider(AuthProvider.LOCAL);
        mailService.sendEmail(user,MailType.REGISTRATION,new Properties());
       return   userRepo.save(user);
    }

    public User save(User user) {
        return userRepo.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    public Optional<User> findByEmail (String email) {
        return userRepo.findUserByEmail(email);
    }

    public User update(Long id,UserDto userDto) {
        Optional<User> inDb = userRepo.findById(id);
        if (inDb.isEmpty()){
            throw new NotFoundException("данный пользователь отсутствует");
        }
        User usr = inDb.get();
        usr.setName(userDto.getName());
        usr.setPassword(userDto.getPassword());
        usr.setEmail(userDto.getEmail());
        usr.setPhone(userDto.getPhone());
        return userRepo.save(usr);
    }
    public void delete(Long id) {
        userRepo.deleteById(id);
    }

}
