package com.emsprojectito.employeeservice.Service.impl;

import com.emsprojectito.employeeservice.Entity.Employee;
import com.emsprojectito.employeeservice.Repository.EmployeeRepository;
import com.emsprojectito.employeeservice.Service.ApiClient;
import com.emsprojectito.employeeservice.Service.EmployeeService;
import com.emsprojectito.employeeservice.dto.ApiResponseDto;
import com.emsprojectito.employeeservice.dto.DepartmentDto;
import com.emsprojectito.employeeservice.dto.EmployeeDto;
import com.emsprojectito.employeeservice.mapper.EmployeeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private ApiClient apiClient;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee employeeSaved = employeeRepository.save(employee);
        EmployeeDto dto = EmployeeMapper.mapToEmployeeDto(employeeSaved);
        return dto;
    }

    @Override
    public ApiResponseDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).get();
        DepartmentDto dto = apiClient.getDepartment(employee.getDepartmentCode());
        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(dto);
        return apiResponseDto;
    }
}
