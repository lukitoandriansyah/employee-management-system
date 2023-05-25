package com.emsprojectito.organizationservice.service;

import com.emsprojectito.organizationservice.dto.OrganizationDto;
import com.emsprojectito.organizationservice.entity.Organization;

public interface OrganizationService {
    OrganizationDto saveOrganization(OrganizationDto organizationDto);
    OrganizationDto getByCode(String organizationCode);
}
