package com.demo.tourist.model;

import com.demo.booking.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Tourist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 512, nullable = false)
    private String name;

    @Column(length = 512)
    private String card;

    @Column(length = 512)
    private String SSN;

    @Column
    private Date birthDate;

    @Column(length = 512)
    private String address;

    @Column
    private int contorBooking;

    @OneToMany(mappedBy = "tourist", cascade = CascadeType.REMOVE)
    private List<Booking> bookings;
}
