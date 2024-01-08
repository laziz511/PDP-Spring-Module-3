package uz.pdp.online.springbootapplication.mapstruct.car;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper
public interface CarMapper {

    @Mapping(target = "id", expression = "java(generateID())")
    Car toEntity(CarDTO dto);

    CarDTO toDto(Car car);

    default String generateID() {
        return UUID.randomUUID().toString();
    }
}
