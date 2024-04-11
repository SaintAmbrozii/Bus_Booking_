package com.example.busbooking.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tickets")
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "busId")
    private Long busId;
    @Column(name = "userId")
    private Long userId;
    @Column(name = "ticket_number")
    private String number;
    @Column(name = "seat")
    private Integer seat;
    @Column(name = "cost")
    private Double cost;
    @Column(name = "booking")
    private Boolean booking;
    @Column(name = "username")
    private String username;
    @Column(name = "surname")
    private String surname;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "serial_pass",length = 6)
    private Integer serialPassport;
    @Column(name = "num_pass",length = 6)
    private Integer numberPassport;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "dep_time")
    private LocalDateTime depTime;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "arr_time")
    private LocalDateTime arrTime;

    @ManyToOne
    @JsonBackReference
    private Booking userBooking;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
