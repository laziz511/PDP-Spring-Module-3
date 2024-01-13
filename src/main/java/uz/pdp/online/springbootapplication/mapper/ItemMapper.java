package uz.pdp.online.springbootapplication.mapper;


import org.mapstruct.Mapper;
import uz.pdp.online.springbootapplication.dto.ItemCreateDTO;
import uz.pdp.online.springbootapplication.entity.Item;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item toEntity(ItemCreateDTO dto);
}
