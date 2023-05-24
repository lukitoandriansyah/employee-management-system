package com.emsprojectito.departmentservice.service.impl;

import com.emsprojectito.departmentservice.dto.DepartmentDto;
import com.emsprojectito.departmentservice.entity.Department;
import com.emsprojectito.departmentservice.mapper.DepartmentMapper;
import com.emsprojectito.departmentservice.repository.DepartmentRepository;
import com.emsprojectito.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        /*Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );*/
        Department department = DepartmentMapper.mapToDepartmentEntity(departmentDto);
        Department savedDepartment = departmentRepository.save(department);

        /*DepartmentDto dto = new DepartmentDto(
                savedDepartment.getId(),
                savedDepartment.getDepartmentName(),
                savedDepartment.getDepartmentDescription(),
                savedDepartment.getDepartmentCode()
        );*/

        DepartmentDto dto = DepartmentMapper.mapToDepartmentDto(department);

        return dto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);
/*        DepartmentDto dto = new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );*/

        DepartmentDto dto = DepartmentMapper.mapToDepartmentDto(department);
        return dto;
    }
}
