package com.demo.tourist.model.dto;

import com.demo.booking.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TouristDTO {
    private Long id;
    private String name;
    private String card;
    private String SSN;
    private Date birthDate;
    private String address;
    private int contorBooking;
    private List<Booking> bookings;
}
