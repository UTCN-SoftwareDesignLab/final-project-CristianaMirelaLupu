package com.demo.emailsender;

import com.demo.booking.model.dto.BookingDTO;
import com.demo.tourist.model.Tourist;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;

public interface EmailService {

    public void sendmail(BookingDTO bookingDTO, Tourist tourist) throws AddressException, MessagingException, IOException;
}
