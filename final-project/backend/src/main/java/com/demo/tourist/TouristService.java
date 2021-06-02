package com.demo.tourist;

import com.demo.tourist.model.Tourist;
import com.demo.tourist.model.dto.TouristDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TouristService {

    @Autowired
    private final TouristRepository touristRepository;
    private final TouristMapper touristMapper;

    Tourist findById(Long id) {
        return touristRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tourist not found: " + id));
    }

    public Tourist findByUsername(String name){
        Tourist tourist = touristRepository.findTouristByName(name)
                .orElseThrow(() -> new RuntimeException("Tourist not found"));
        return tourist;
    }

    public List<TouristDTO> findAll() {
        return touristRepository.findAll().stream()
                .map(touristMapper::toDto)
                .collect(Collectors.toList());
    }

    public TouristDTO create(TouristDTO tourist) {

        return touristMapper.toDto(touristRepository.save(touristMapper.toTourist(tourist)));
    }

    public TouristDTO edit(TouristDTO tourist) {

        Tourist actTourist = findById(tourist.getId());

        actTourist.setName(tourist.getName());
        actTourist.setCard(tourist.getCard());
        actTourist.setSSN(tourist.getSSN());
        actTourist.setBirthDate(tourist.getBirthDate());
        actTourist.setAddress(tourist.getAddress());
        actTourist.setContorBooking(actTourist.getContorBooking());
        actTourist.setBookings(tourist.getBookings());

        return touristMapper.toDto(
                touristRepository.save(actTourist)
        );
    }

    public void deleteAll (){
        touristRepository.deleteAll();
    }

    public void deleteById (Long id) {
        touristRepository.deleteById(id);
    }

    public List<TouristDTO> findLoyal() {
        List<TouristDTO> all =  touristRepository.findAll().stream()
                .map(touristMapper::toDto)
                .collect(Collectors.toList());

        List<TouristDTO> result = new ArrayList<>();

        for (TouristDTO t : all) {
            if (t.getContorBooking() > 0) {
                result.add(t);
            }
        }
        return result;
    }
}
