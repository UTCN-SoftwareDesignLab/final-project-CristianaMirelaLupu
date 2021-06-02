package com.demo.booking.model;

import com.demo.tourist.model.Tourist;
import com.demo.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data

public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 512, nullable = false)
    private String description;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "tourist_id")
    private Tourist tourist;

    @Column(nullable = false)
    private int room;

    @Column(nullable = false)
    private LocalDateTime checkin;

    @Column(nullable = false)
    private LocalDateTime checkout;

    @Column(nullable = false)
    private int price;

}


