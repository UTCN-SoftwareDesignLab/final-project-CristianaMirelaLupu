package com.demo.booking;

import com.demo.booking.model.dto.BookingDTO;
import com.demo.booking.model.Booking;
import com.demo.tourist.TouristMapper;
import com.demo.tourist.TouristRepository;
import com.demo.tourist.TouristService;
import com.demo.tourist.model.Tourist;
import com.demo.user.UserRepository;
import com.demo.user.UserService;
import com.demo.user.mapper.UserMapper;
import com.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    @Autowired
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final TouristMapper touristMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TouristRepository touristRepository;
    private final UserService userService;
    private final TouristService touristService;


    Booking findById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found: " + id));
    }

    public List<BookingDTO> findAll() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");

        return bookingRepository.findAll().stream()
                .map(consultation -> bookingMapper.toDto(consultation, formatter))
                .collect(Collectors.toList());
    }

    public BookingDTO create(BookingDTO booking) throws Exception {

        Tourist tourist = touristService.findByUsername(booking.getTourist());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        LocalDateTime checkin = LocalDateTime.parse(booking.getCheckin(), formatter);
        LocalDateTime checkout = LocalDateTime.parse(booking.getCheckout(), formatter);
        Period periodd = Period.between(checkout.toLocalDate(), checkin.toLocalDate());
        int price = periodd.getDays() * 50;

        if(tourist.getContorBooking() != 0){
            price = price - 25;
        }

        int loialty = tourist.getContorBooking() + 1;
        tourist.setContorBooking(loialty);

        Booking actBooking = new Booking().builder()
                .id(booking.getId())
                .description(booking.getDescription())
                .tourist(tourist)
                .room(booking.getRoom())
                .checkin(checkin)
                .checkout(checkout)
                .price(price)
                .build();

        List<BookingDTO> all = findAll();
        List<BookingDTO> overlay = new ArrayList<>();

        for (BookingDTO b : all) {
            LocalDateTime bCheckin = LocalDateTime.parse(b.getCheckin(), formatter);
            LocalDateTime bCheckout = LocalDateTime.parse(b.getCheckout(), formatter);
            //Duration duration = Duration.between(bCheckin, bCheckout);
            Period period = Period.between(bCheckout.toLocalDate(), actBooking.getCheckout().toLocalDate());

            if (period.getDays() <= 0) {

                if ( b.getTourist().equals(actBooking.getTourist()) || b.getRoom() == actBooking.getRoom() ) {
                    overlay.add(b);
                }
            }
        }

        if (overlay.isEmpty()) {
            return bookingMapper.toDto(bookingRepository.save(actBooking), formatter);
        } else {
            throw new Exception("Invalid date and time");
        }
    }

    public BookingDTO edit(BookingDTO booking) throws Exception {

        Tourist tourist = touristService.findByUsername(booking.getTourist());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");

        LocalDateTime checkin = LocalDateTime.parse(booking.getCheckin(), formatter);
        LocalDateTime checkout = LocalDateTime.parse(booking.getCheckout(), formatter);


        Booking actBooking = new Booking().builder()
                .id(booking.getId())
                .description(booking.getDescription())
                .tourist(tourist)
                .room(booking.getRoom())
                .checkin(checkin)
                .checkout(checkout)
                .build();

        List<BookingDTO> all = findAll();
        List<BookingDTO> overlay = new ArrayList<>();


        for (BookingDTO b : all) {
            LocalDateTime bCheckin = LocalDateTime.parse(b.getCheckin(), formatter);
            LocalDateTime bCheckout = LocalDateTime.parse(b.getCheckout(), formatter);
            //Duration duration = Duration.between(bCheckin, bCheckout);
            Period period = Period.between(bCheckout.toLocalDate(), actBooking.getCheckout().toLocalDate());

            if (period.getDays() <= 0) {

                if ( b.getTourist().equals(actBooking.getTourist()) || b.getRoom() == actBooking.getRoom() ) {
                    overlay.add(b);
                }
            }
        }

        if (overlay.isEmpty()) {
            return bookingMapper.toDto(bookingRepository.save(actBooking), formatter);
        } else {
            throw new Exception("Invalid date and time");
        }
    }


    public void deleteAll (){
        bookingRepository.deleteAll();
    }

    public void deleteById (Long id) {
        bookingRepository.deleteById(id);
    }

}