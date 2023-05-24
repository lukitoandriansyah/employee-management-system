package com.emsprojectito.employeeservice.Service.impl;

import com.emsprojectito.employeeservice.Entity.Employee;
import com.emsprojectito.employeeservice.Repository.EmployeeRepository;
import com.emsprojectito.employeeservice.Service.ApiClient;
import com.emsprojectito.employeeservice.Service.EmployeeService;
import com.emsprojectito.employeeservice.dto.ApiResponseDto;
import com.emsprojectito.employeeservice.dto.DepartmentDto;
import com.emsprojectito.employeeservice.dto.EmployeeDto;
import com.emsprojectito.employeeservice.mapper.EmployeeMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER= LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeRepository employeeRepository;
    private ApiClient apiClient;
    private WebClient webClient;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee employeeSaved = employeeRepository.save(employee);
        EmployeeDto dto = EmployeeMapper.mapToEmployeeDto(employeeSaved);
        return dto;
    }

/*    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")*/
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public ApiResponseDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).get();
        /*DepartmentDto dto = apiClient.getDepartment(employee.getDepartmentCode());*/

        LOGGER.info("inside getEmployeeById method");

        DepartmentDto dto = webClient.get()
                .uri("http://localhost:8080/api/departments/"+employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(dto);
        return apiResponseDto;
    }

    public ApiResponseDto getDefaultDepartment(Long employeeId, Exception e){
        Employee employee = employeeRepository.findById(employeeId).get();

        LOGGER.info("inside getDefaultDepartment method");

        DepartmentDto dto = new DepartmentDto();
        dto.setDepartmentName("R&D Department");
        dto.setDepartmentCode("R&D 001");
        dto.setDepartmentDescription("Research and Development Department");

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(dto);
        return apiResponseDto;
    }
}
