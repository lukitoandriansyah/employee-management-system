package com.emsprojectito.organizationservice.controller;

import com.emsprojectito.organizationservice.dto.OrganizationDto;
import com.emsprojectito.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/organization")
public class OrganizationController {
    private OrganizationService organizationService;

    @PostMapping("/save")
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto){
        return new ResponseEntity<>(organizationService.saveOrganization(organizationDto), HttpStatus.CREATED);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<OrganizationDto> getByCode(@PathVariable("code") String organizationCode){
        return new ResponseEntity<>(organizationService.getByCode(organizationCode), HttpStatus.OK);
    }
}
