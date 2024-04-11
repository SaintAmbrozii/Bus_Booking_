package com.example.busbooking.repository;

import com.example.busbooking.domain.Booking;
import com.example.busbooking.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepo extends JpaRepository<Booking,Long> {

    boolean existsBookingByUser(User user);

    Optional<Booking> findBookingByBusId(Long id);

    Optional<Booking> findByUserIdAndPayedIsFalse(Long id);

    Optional<Booking> findBookingByUser_Id(Long id);

    Optional<Booking> findByUserId(Long id);

    Optional<Booking> findByBusIdAndUserId(Long busId,Long userId);

    List<Booking> findAllByUserId(Long id);

}
