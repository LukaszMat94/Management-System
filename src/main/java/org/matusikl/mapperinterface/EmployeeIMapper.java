package org.matusikl.mapperinterface;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.matusikl.dto.employeedto.EmployeeAccountDto;
import org.matusikl.dto.employeedto.EmployeeGetDto;
import org.matusikl.dto.employeedto.EmployeeJobDto;
import org.matusikl.dto.employeedto.EmployeeLaptopDto;
import org.matusikl.dto.employeedto.EmployeePostDto;
import org.matusikl.dto.employeedto.EmployeeRoleDto;
import org.matusikl.dto.employeedto.EmployeeTaskDto;
import org.matusikl.dto.taskdto.TaskGetDto;
import org.matusikl.model.Employee;
import org.matusikl.model.Task;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeIMapper {

    EmployeeIMapper INSTANCE = Mappers.getMapper( EmployeeIMapper.class );

    EmployeeGetDto employeeToEmployeeGetDto(Employee employee);

    Employee employeeGetDtoToEmployee(EmployeeGetDto employeeGetDto);

    EmployeePostDto employeeToEmployeePostDto(Employee employee);

    Employee employeePostDtoToEmployee(EmployeePostDto employeePostDto);

    EmployeeAccountDto employeeToEmployeeAccountDto(Employee employee);

    EmployeeLaptopDto employeeToEmployeeLaptopDto(Employee employee);

    EmployeeJobDto employeeToEmployeeJobDto(Employee employee);

    EmployeeRoleDto employeeToEmployeeRoleDto(Employee employee);

    EmployeeTaskDto employeeToEmployeeTaskDto(Employee employee);

    List<EmployeeGetDto> listEmployeeToListEmployeeGetDto(List<Employee> employee);

    List<Employee> listEmployeeGetDtoToListEmployee(List<EmployeeGetDto> employeeGetDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEmployeeFromEmployeePostDto(EmployeePostDto employeePostDto, @MappingTarget Employee employee);

    Task taskToTaskGetDto(Task task);

    TaskGetDto taskGetDtoToTask(TaskGetDto taskGetDto);

}

