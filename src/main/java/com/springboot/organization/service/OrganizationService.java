package com.springboot.organization.service;

import com.springboot.organization.payload.OrganizationDTO;

import java.util.List;

public interface OrganizationService {
    OrganizationDTO createOrganization(OrganizationDTO organizationDTO);

    List<OrganizationDTO> getAllOrganizations();

    OrganizationDTO getOrganizationById(long id);

    OrganizationDTO updateOrganization(OrganizationDTO organizationDTO, long id);

    void deleteOrganizationById(long id);
}
