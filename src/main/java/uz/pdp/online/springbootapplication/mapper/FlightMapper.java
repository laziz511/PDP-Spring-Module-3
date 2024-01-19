package uz.pdp.online.springbootapplication.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import uz.pdp.online.springbootapplication.dto.FlightDTO;
import uz.pdp.online.springbootapplication.entity.Flight;

@Mapper(componentModel = "spring")
public interface FlightMapper {

    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);

    @Mapping(target = "departureAirport", source = "departureAirport")
    @Mapping(target = "arrivalAirport", source = "arrivalAirport")
    @Mapping(target = "agent", source = "agent")
    FlightDTO toDTO(Flight flight);

    @Mapping(target = "departureAirport", source = "departureAirport")
    @Mapping(target = "arrivalAirport", source = "arrivalAirport")
    @Mapping(target = "agent", source = "agent")
    Flight toEntity(FlightDTO flightDTO);
}