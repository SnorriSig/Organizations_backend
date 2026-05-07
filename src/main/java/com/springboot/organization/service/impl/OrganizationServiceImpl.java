package com.springboot.organization.service.impl;

import com.springboot.organization.entity.Organization;
import com.springboot.organization.payload.OrganizationDTO;
import com.springboot.organization.repository.OrganizationRepository;
import com.springboot.organization.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public OrganizationDTO createOrganization(OrganizationDTO organizationDTO) {

        // convert DTO to entity
        Organization organization = new Organization();
        organization.setName(organizationDTO.getName());
        organization.setDescription(organizationDTO.getDescription());
        organization.setLocation(organization.getLocation());

        Organization newOrganization = organizationRepository.save(organization);

        // convert entity to DTO
        OrganizationDTO organizationResponse = new OrganizationDTO();
        organizationResponse.setId(newOrganization.getId());
        organizationResponse.setName(newOrganization.getName());
        organizationResponse.setDescription(newOrganization.getDescription());
        organizationResponse.setLocation(newOrganization.getLocation());

        return organizationResponse;
    }
}
