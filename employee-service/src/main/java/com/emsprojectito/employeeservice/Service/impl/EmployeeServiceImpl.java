package com.emsprojectito.employeeservice.Service.impl;

import com.emsprojectito.employeeservice.Entity.Employee;
import com.emsprojectito.employeeservice.Repository.EmployeeRepository;
import com.emsprojectito.employeeservice.Service.EmployeeService;
import com.emsprojectito.employeeservice.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee =new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );

        Employee employeeSaved = employeeRepository.save(employee);
        EmployeeDto dto = new EmployeeDto(
                employeeSaved.getId(),
                employeeSaved.getFirstName(),
                employeeSaved.getLastName(),
                employeeSaved.getEmail()
        );

        return dto;
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        EmployeeDto employeeDto = new EmployeeDto();
        if (employee.isPresent()){
            employeeDto.setId(employee.get().getId());
            employeeDto.setFirstName(employee.get().getFirstName());
            employeeDto.setLastName(employee.get().getLastName());
            employeeDto.setEmail(employee.get().getEmail());
        }
        return employeeDto;
    }
}
