package com.demo.booking;

import com.demo.UrlMapping;
import com.demo.booking.model.dto.BookingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlMapping.BOOKING)
@RequiredArgsConstructor

public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public List<BookingDTO> allBookings() {
        return bookingService.findAll();
    }

    @PostMapping
    public BookingDTO create(@RequestBody BookingDTO booking) throws Exception {
        return bookingService.create(booking);
    }

    @PutMapping
    public BookingDTO edit(@RequestBody BookingDTO booking) throws Exception{
        return bookingService.edit(booking);
    }

    @DeleteMapping
    public void deleteAll() { bookingService.deleteAll(); }

    @DeleteMapping(UrlMapping.ID)
    public void deleteById(@PathVariable String id) {
        bookingService.deleteById(Long.parseLong(id));}
}
