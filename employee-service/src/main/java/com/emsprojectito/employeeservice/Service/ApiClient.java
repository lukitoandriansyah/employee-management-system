package com.emsprojectito.employeeservice.Service;

import com.emsprojectito.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(url = "http://localhost:8080;http://localhost:8083",value = "DEPARTMENT-SERVICE")
@FeignClient(name = "DEPARTMENT-SERVICE")
public interface ApiClient {
    @GetMapping("api/departments/{code}")
    DepartmentDto getDepartment(@PathVariable("code") String departmentCode);
}
