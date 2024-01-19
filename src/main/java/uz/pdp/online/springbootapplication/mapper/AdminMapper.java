package uz.pdp.online.springbootapplication.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uz.pdp.online.springbootapplication.dto.AdminDTO;
import uz.pdp.online.springbootapplication.entity.Admin;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

    AdminDTO toDTO(Admin admin);

    Admin toEntity(AdminDTO adminDTO);
}