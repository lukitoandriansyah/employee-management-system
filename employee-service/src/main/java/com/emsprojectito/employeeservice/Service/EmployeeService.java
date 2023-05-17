package com.emsprojectito.employeeservice.Service;

import com.emsprojectito.employeeservice.dto.ApiResponseDto;
import com.emsprojectito.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    //EmployeeDto getEmployeeById(Long id);
    ApiResponseDto getEmployeeById(Long id);
}
