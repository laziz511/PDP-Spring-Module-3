package uz.pdp.online.springbootapplication.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import uz.pdp.online.springbootapplication.dto.AgentDTO;
import uz.pdp.online.springbootapplication.entity.Agent;

@Mapper(componentModel = "spring")
public interface AgentMapper {

    AgentMapper INSTANCE = Mappers.getMapper(AgentMapper.class);

    @Mapping(target = "company", source = "company")
    AgentDTO toDTO(Agent agent);

    @Mapping(target = "company", source = "company")
    Agent toEntity(AgentDTO agentDTO);
}