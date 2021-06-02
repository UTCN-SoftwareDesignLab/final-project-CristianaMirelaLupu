package com.demo.emailsender;

import com.demo.booking.model.dto.BookingDTO;
import com.demo.tourist.model.Tourist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;


@Service
@RequiredArgsConstructor
public class EmailSender implements EmailService{
    public void sendmail(BookingDTO bookingDTO, Tourist tourist) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("cristianawolf18@gmail.com", "cristianawolf18**");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("cristianawolf18@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(tourist.getAddress()));
        msg.setSubject("New reservation at our lovely hotel");
        msg.setContent("Hi, you have an accomodation starting with: " + bookingDTO.getCheckin() + ", until: " +
                bookingDTO.getCheckout() + "." + " You have to pay: " +  bookingDTO.getPrice() +"$." ,"text/html");
        msg.setSentDate(new Date());

        Transport.send(msg);
    }
}
