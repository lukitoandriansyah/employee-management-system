package com.emsprojectito.organizationservice.service.impl;

import com.emsprojectito.organizationservice.dto.OrganizationDto;
import com.emsprojectito.organizationservice.entity.Organization;
import com.emsprojectito.organizationservice.mapper.OrganizationMapper;
import com.emsprojectito.organizationservice.repository.OrganizationRepository;
import com.emsprojectito.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private OrganizationRepository organizationRepository;
    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);
        Organization organizationSaved = organizationRepository.save(organization);
        OrganizationDto dto = OrganizationMapper.mapToOrganizationDto(organizationSaved);
        return dto;
    }

    @Override
    public OrganizationDto getByCode(String organizationCode) {
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        OrganizationDto dto = OrganizationMapper.mapToOrganizationDto(organization);
        return dto;
    }
}
