package com.example.busbooking.service;

import com.example.busbooking.domain.Booking;
import com.example.busbooking.domain.Payment;
import com.example.busbooking.domain.User;
import com.example.busbooking.exception.NoSuchMoneyException;
import com.example.busbooking.repository.BookingRepo;
import com.example.busbooking.repository.PaymentRepo;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class PaymentService {

    private final BookingRepo bookingRepo;
    private final PaymentRepo paymentRepo;


    private final LocalDateTime currentDateTime = LocalDateTime.now();

    public PaymentService(BookingRepo bookingRepo, PaymentRepo paymentRepo) {
        this.bookingRepo = bookingRepo;
        this.paymentRepo = paymentRepo;

    }

    @Transactional
    public Payment accept(Long id, User user, Double summa) {
        Booking booking = bookingRepo.findById(id).orElseThrow();
        Payment payment = new Payment();
        if (!booking.getLockExpiry().isAfter(currentDateTime)) {
            if (booking.getSumma().equals(summa)) {
               payment.setBooking(booking);
               payment.setSumma(summa);
               payment.setUser(user);
            }
            else {
                throw new NoSuchMoneyException("недостаточно средств для платежа");
            }

        }
        booking.setPayed(true);
        bookingRepo.save(booking);
        return paymentRepo.save(payment);
    }

    public Optional<Payment> findById(Long id) {
        return paymentRepo.findById(id);
    }
}
