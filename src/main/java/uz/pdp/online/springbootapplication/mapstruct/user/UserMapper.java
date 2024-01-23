package uz.pdp.online.springbootapplication.mapstruct.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(target = "passportSerial", source = "passportDTO.serial")
    @Mapping(target = "passportNumber", source = "passportDTO.number")
    @Mapping(target = "addressRegion", source = "addressDTO.region")
    @Mapping(target = "addressCity", source = "addressDTO.city")
    @Mapping(target = "addressStreet", source = "addressDTO.street")
    @Mapping(target = "addressHome", source = "addressDTO.home")
    User toEntity(AuthUserDTO authUserDTO, PassportDTO passportDTO, AddressDTO addressDTO);
}
