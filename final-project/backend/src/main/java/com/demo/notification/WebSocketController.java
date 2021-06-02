package com.demo.notification;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class WebSocketController {
    @MessageMapping("/checkOut")
    @SendTo("/housekeeperbooking/checkOutNotification")
    public HousekeeperNotification checkOut(TouristLeft receiveMessage) throws Exception {
        return new HousekeeperNotification("Tourist " + receiveMessage.getTouristName()
                + ", has left at " + receiveMessage.getCheckOut(), receiveMessage.getHousekeeperUsername());
    }
}
