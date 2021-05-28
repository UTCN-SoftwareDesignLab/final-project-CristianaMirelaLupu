package com.demo.tourist;

import com.demo.tourist.model.Tourist;
import com.demo.tourist.model.dto.TouristDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TouristMapper {

    TouristDTO toDto(Tourist tourist);

    Tourist toTourist(TouristDTO tourist);

}