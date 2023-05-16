package com.emsprojectito.departmentservice.controller;

import com.emsprojectito.departmentservice.dto.DepartmentDto;
import com.emsprojectito.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;

@AllArgsConstructor
@RestController
@RequestMapping("api/departments")
public class DepartmentController {
    private DepartmentService departmentService;

    @PostMapping("/save")
    public ResponseEntity<DepartmentDto> savedDepartment(@RequestBody DepartmentDto departmentDto){
        return new ResponseEntity<>(departmentService.saveDepartment(departmentDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("id") String departmentCode){
        return new ResponseEntity<>(departmentService.getDepartmentByCode(departmentCode), HttpStatus.OK);
    }
}
