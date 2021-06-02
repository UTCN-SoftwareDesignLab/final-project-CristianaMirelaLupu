package com.demo.notification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class TouristLeft {

    private String touristName;
    private String checkOut;
    private String housekeeperUsername;
}