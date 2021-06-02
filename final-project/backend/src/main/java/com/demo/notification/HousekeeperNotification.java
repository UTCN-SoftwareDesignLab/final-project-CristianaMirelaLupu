package com.demo.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class HousekeeperNotification {
    private String message;
    private String housekeeperUsername;
}

