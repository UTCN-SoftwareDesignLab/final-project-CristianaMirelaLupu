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

//    @Mappings({
//            @Mapping(target = "patient.name", source = "patient"),
//            @Mapping(target = "doctor.username", source = "doctor"),
//            @Mapping(target = "dateAndTime", source = "consultationDTO.dateAndTime", qualifiedByName = "dateAndTime")
//    })
//    Consultation toConsultation(ConsultationDTO consultationDTO, @Context DateTimeFormatter dateTimeFormatter);

    @Named("checkin")
    default String mapCheckin(LocalDateTime checkin, @Context DateTimeFormatter dateTimeFormatter) {

        //LocalDateTime localDateTime = LocalDateTime.parse(booking, dateTimeFormatter);
       // ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of(consultationDTO.getTimeZoneStart()));
        return checkin.format(dateTimeFormatter);
    }

    @Named("checkout")
    default String mapCheckout(LocalDateTime checkout, @Context DateTimeFormatter dateTimeFormatter) {

        //LocalDateTime localDateTime = LocalDateTime.parse(booking, dateTimeFormatter);
        // ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of(consultationDTO.getTimeZoneStart()));
        return checkout.format(dateTimeFormatter);
    }

}


//    @Mapping(target = "createdTime", ignore = true)
//    @Mapping(target = "startTime", source = "dto", qualifiedByName = "startTime")
//    @Mapping(target = "endTime", source = "dto", qualifiedByName = "endTime")
//    Appointment convertAppointmentDTOToAppointment(AppointmentDTO dto, @Context DateTimeFormatter dateTimeFormatter);
//
//    @Named("startTime")
//    default OffsetDateTime mapStartTime(AppointmentDTO dto, @Context DateTimeFormatter dateTimeFormatter) {
//
//        LocalDateTime localDateTime = LocalDateTime.parse(dto.getStartTime(), dateTimeFormatter);
//        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of(dto. getTimeZoneStart()));
//        return zonedDateTime.toOffsetDateTime()
//    }
//
//    @Named("endTime")
//    default OffsetDateTime mapEndTime(AppointmentDTO dto, @Context DateTimeFormatter dateTimeFormatter) {
//
//        LocalDateTime localDateTime = LocalDateTime.parse(dto.getEndTime(), dateTimeFormatter);
//        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of(dto. getTimeZoneEnd()));
//        return zonedDateTime.toOffsetDateTime()
//    }
//
//
//
//
//}
