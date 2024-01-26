package uz.pdp.online.springbootapplication.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import uz.pdp.online.springbootapplication.dto.CustomerDTO;
import uz.pdp.online.springbootapplication.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "cityOfResidence", source = "cityOfResidence")
    CustomerDTO toDTO(Customer customer);

    @Mapping(target = "cityOfResidence", source = "cityOfResidence")
    Customer toEntity(CustomerDTO customerDTO);
}