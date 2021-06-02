package com.demo.booking;

import com.demo.booking.model.Booking;
import com.demo.booking.model.dto.BookingDTO;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Mapper(componentModel = "spring")
    public interface BookingMapper {

    @Mappings({
            @Mapping(target = "tourist", expression = "java(booking.getTourist().getName())"),
            @Mapping(target = "checkin", source = "booking.checkin", qualifiedByName = "checkin"),
            @Mapping(target = "checkout", source = "booking.checkout", qualifiedByName = "checkout")
    })
    BookingDTO toDto(Booking booking, @Context DateTimeFormatter dateTimeFormatter);

    @Named("checkin")
    default String mapCheckin(LocalDateTime checkin, @Context DateTimeFormatter dateTimeFormatter) {
        return checkin.format(dateTimeFormatter);
    }

    @Named("checkout")
    default String mapCheckout(LocalDateTime checkout, @Context DateTimeFormatter dateTimeFormatter) {
        return checkout.format(dateTimeFormatter);
    }
}

