package uz.pdp.online.springbootapplication.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uz.pdp.online.springbootapplication.dto.CityDTO;
import uz.pdp.online.springbootapplication.entity.City;

@Mapper(componentModel = "spring")
public interface CityMapper {

    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    CityDTO toDTO(City city);

    City toEntity(CityDTO cityDTO);
}