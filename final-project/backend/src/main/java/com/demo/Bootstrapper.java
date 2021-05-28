package com.demo;

import com.demo.booking.BookingRepository;
import com.demo.booking.BookingService;
import com.demo.tourist.TouristService;
import com.demo.tourist.model.dto.TouristDTO;
import com.demo.security.AuthService;
import com.demo.tourist.TouristRepository;
import com.demo.security.dto.SignupRequest;
import com.demo.user.RoleRepository;
import com.demo.user.UserRepository;
import com.demo.user.model.ERole;
import com.demo.user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final AuthService authService;

    private final TouristRepository touristRepository;

    private final TouristService touristService;

    private final BookingRepository bookingRepository;

    private final BookingService bookingService;

    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {
            touristRepository.deleteAll();
            bookingRepository.deleteAll();
            userRepository.deleteAll();
            roleRepository.deleteAll();

            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            authService.register(SignupRequest.builder()
                    .email("admin.admin@gmail.com")
                    .username("admin")
                    .password("Aleluia123!")
                    .roles(Set.of("ADMIN"))
                    .build());

            authService.register(SignupRequest.builder()
                    .email("receptionist.receptionist@gmail.com")
                    .username("receptionist")
                    .password("Aleluia123!")
                    .roles(Set.of("RECEPTIONIST"))
                    .build());

            authService.register(SignupRequest.builder()
                    .email("housekeeper.housekeeper@gmail.com")
                    .username("housekeeper")
                    .password("Aleluia123!")
                    .roles(Set.of("HOUSEKEEPER"))
                    .build());

            touristService.create(TouristDTO.builder()
                    .name("tourist1")
                    .card("1234")
                    .SSN("123445678910")
                    .birthDate(Date.valueOf(LocalDate.now()))
                    .address("Cluj-Napoca")
                    .build());

            touristService.create(TouristDTO.builder()
                    .name("tourist2")
                    .card("9874")
                    .SSN("123445678911")
                    .birthDate(Date.valueOf(LocalDate.now()))
                    .address("Cluj-Napoca")
                    .build());
        }
    }
}
