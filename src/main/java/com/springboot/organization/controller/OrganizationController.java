package com.springboot.organization.controller;

import com.springboot.organization.entity.Organization;
import com.springboot.organization.payload.OrganizationDTO;
import com.springboot.organization.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {

    private OrganizationService organizationService;

    // Dependency injection
    // Constructor injection
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    // creates organization post rest api
    @PostMapping
    public ResponseEntity<OrganizationDTO> createOrganization(@RequestBody OrganizationDTO organizationDTO) {
        return new ResponseEntity<>(organizationService.createOrganization(organizationDTO), HttpStatus.CREATED);
    }

    // creates get all organizations rest api
    @GetMapping
    public List<OrganizationDTO> getAllOrganizations() {
        return organizationService.getAllOrganizations();
    }

    // creates get organization by id api
    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDTO> getOrganizationById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(organizationService.getOrganizationById(id));
    }

    // update organization by id api
    @PutMapping("/{id}")
    public ResponseEntity<OrganizationDTO> updateOrganization(@RequestBody OrganizationDTO organizationDTO, @PathVariable(name = "id") long id) {

        OrganizationDTO organizationResponse = organizationService.updateOrganization(organizationDTO, id);

        return new ResponseEntity<>(organizationResponse, HttpStatus.OK);
    }

    // delete organization by id api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrganization(@PathVariable(name = "id") long id) {

        organizationService.deleteOrganizationById(id);

        return new ResponseEntity<>("Organization delete successfully.", HttpStatus.OK);
    }
}
