package com.demo.tourist;

import com.demo.UrlMapping;
import com.demo.tourist.model.dto.TouristDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlMapping.TOURIST)
@RequiredArgsConstructor
public class TouristController {

    private final TouristService touristService;

    @GetMapping
    public List<TouristDTO> allTourist() {
        return touristService.findAll();
    }

    @PostMapping
    public TouristDTO create(@RequestBody TouristDTO tourist) {
        return touristService.create(tourist);
    }

    @PatchMapping
    public TouristDTO edit(@RequestBody TouristDTO tourist) {
        return touristService.edit(tourist);
    }

    @DeleteMapping
    public void deleteAll() { touristService.deleteAll(); }

    @DeleteMapping(UrlMapping.ID)
    public void deleteById(@PathVariable String id) {
        touristService.deleteById(Long.parseLong(id));}
}
