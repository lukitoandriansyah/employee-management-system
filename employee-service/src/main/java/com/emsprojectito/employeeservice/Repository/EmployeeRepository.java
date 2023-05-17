package com.emsprojectito.employeeservice.Repository;

import com.emsprojectito.employeeservice.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
