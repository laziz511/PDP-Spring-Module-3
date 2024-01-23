package uz.pdp.online.springbootapplication.mapstruct.employee;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface EmployeeMapper {
    @Mapping(target = "id", source = "employeeId")
    @Mapping(target = "name", source = "employeeName")
    Employee toEntity(EmployeeDTO dto);

    @InheritInverseConfiguration
    EmployeeDTO toDto(Employee employee);

}
