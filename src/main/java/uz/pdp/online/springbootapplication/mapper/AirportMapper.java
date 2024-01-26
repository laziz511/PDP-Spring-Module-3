package uz.pdp.online.springbootapplication.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import uz.pdp.online.springbootapplication.dto.AirportDTO;
import uz.pdp.online.springbootapplication.entity.Airport;

@Mapper(componentModel = "spring")
public interface AirportMapper {

    AirportMapper INSTANCE = Mappers.getMapper(AirportMapper.class);

    @Mapping(target = "city", source = "city")
    AirportDTO toDTO(Airport airport);

    @Mapping(target = "city", source = "city")
    Airport toEntity(AirportDTO airportDTO);
}
