package com.emsprojectito.employeeservice.Repository;

import com.emsprojectito.employeeservice.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
