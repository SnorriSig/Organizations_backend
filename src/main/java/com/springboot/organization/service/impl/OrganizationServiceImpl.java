package com.springboot.organization.service.impl;

import com.springboot.organization.entity.Organization;
import com.springboot.organization.exception.ResourceNotFoundException;
import com.springboot.organization.payload.OrganizationDTO;
import com.springboot.organization.repository.OrganizationRepository;
import com.springboot.organization.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public OrganizationDTO createOrganization(OrganizationDTO organizationDTO) {

        // convert DTO to entity
        Organization organization = mapToEntity(organizationDTO);
        Organization newOrganization = organizationRepository.save(organization);

        // convert entity to DTO
        OrganizationDTO organizationResponse = mapToDTO(newOrganization);
        return organizationResponse;
    }

    @Override
    public List<OrganizationDTO> getAllOrganizations() {

        List<Organization> organizations = organizationRepository.findAll();
        return organizations.stream().map(organization -> mapToDTO(organization)).collect(Collectors.toList());
    }

    @Override
    public OrganizationDTO getOrganizationById(long id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organization", "id", id));
        return mapToDTO(organization);
    }

    @Override
    public OrganizationDTO updateOrganization(OrganizationDTO organizationDTO, long id) {
        // Get organization by id from database
        Organization organization = organizationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Organization", "id", id));

        organization.setName(organizationDTO.getName());
        organization.setDescription(organizationDTO.getDescription());
        organization.setLocation(organizationDTO.getLocation());

        Organization updatedOrganization = organizationRepository.save(organization);
        return mapToDTO(updatedOrganization);
    }

    // convert DTO into Entity
    private Organization mapToEntity(OrganizationDTO organizationDTO) {
        Organization organization = new Organization();

        organization.setName(organizationDTO.getName());
        organization.setDescription(organizationDTO.getDescription());
        organization.setLocation(organizationDTO.getLocation());

        return organization;
    }

    // convert Entity into DTO
    private OrganizationDTO mapToDTO(Organization organization) {
        OrganizationDTO organizationDTO = new OrganizationDTO();

        organizationDTO.setId(organization.getId());
        organizationDTO.setName(organization.getName());
        organizationDTO.setDescription(organization.getDescription());
        organizationDTO.setLocation(organization.getLocation());

        return organizationDTO;
    }
}
