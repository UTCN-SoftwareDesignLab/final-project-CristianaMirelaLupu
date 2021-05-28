package com.demo.booking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class BookingDTO {
    private Long id;
    private String description;
    private String tourist;
    private int room;
    private String checkin;
    private String checkout;
}
