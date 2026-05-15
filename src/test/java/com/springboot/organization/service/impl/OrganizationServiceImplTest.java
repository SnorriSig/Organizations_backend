package com.springboot.organization.service.impl;

import com.springboot.organization.entity.Organization;
import com.springboot.organization.exception.ResourceNotFoundException;
import com.springboot.organization.payload.OrganizationDTO;
import com.springboot.organization.repository.OrganizationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrganizationServiceImplTest {

    @Mock
    private OrganizationRepository organizationRepository;

    @InjectMocks // Will inject repo mock into the service instance I want to test
    private OrganizationServiceImpl organizationService;

    @Test
    void getAllOrganizations_shouldReturnOrganizationDTOs() {
        // given
        Organization organization = new Organization();
        organization.setId(1L);
        organization.setName("Test Org");
        organization.setDescription("Test Description");
        organization.setLocation("Copenhagen");
        when(organizationRepository.findAll())
                .thenReturn(List.of(organization));
        // when
        List<OrganizationDTO> result = organizationService.getAllOrganizations();
        // then
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Test Org", result.get(0).getName());
        assertEquals("Test Description", result.get(0).getDescription());
        assertEquals("Copenhagen", result.get(0).getLocation());
        verify(organizationRepository).findAll(); }

    @Test
    void createOrganization_shouldSaveAndReturnOrganizationDTO() {
        OrganizationDTO inputDto = new OrganizationDTO();
        inputDto.setName("Test Org");
        inputDto.setDescription("Test Description");
        inputDto.setLocation("Copenhagen");

        Organization savedOrganization = new Organization();
        savedOrganization.setId(1L);
        savedOrganization.setName("Test Org");
        savedOrganization.setDescription("Test Description");
        savedOrganization.setLocation("Copenhagen");

        when(organizationRepository.save(any(Organization.class)))
                .thenReturn(savedOrganization);

        OrganizationDTO result = organizationService.createOrganization(inputDto);

        assertEquals(1L, result.getId());
        assertEquals("Test Org", result.getName());
        assertEquals("Test Description", result.getDescription());
        assertEquals("Copenhagen", result.getLocation());

        verify(organizationRepository, times(1)).save(any(Organization.class));
    }

    @Test
    void getOrganizationById_whenOrganizationExists_shouldReturnOrganizationDTO() {
        Organization organization = new Organization();
        organization.setId(1L);
        organization.setName("Test Org");
        organization.setDescription("Test Description");
        organization.setLocation("Copenhagen");

        when(organizationRepository.findById(1L))
                .thenReturn(Optional.of(organization));

        OrganizationDTO result = organizationService.getOrganizationById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Test Org", result.getName());
        assertEquals("Test Description", result.getDescription());
        assertEquals("Copenhagen", result.getLocation());
    }

    @Test
    void getOrganizationById_whenOrganizationDoesNotExist_shouldThrowException() {
        when(organizationRepository.findById(10L))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            organizationService.getOrganizationById(10L);
        });
    }
}
