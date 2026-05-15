package com.springboot.organization.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.organization.payload.OrganizationDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class OrganizationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc; // fake browser

    @Autowired
    private ObjectMapper objectMapper; // JAVA to JSON

    private OrganizationDTO organizationDTO;

    @BeforeEach
    void setUp() {
        organizationDTO = new OrganizationDTO();

        organizationDTO.setName("Test Org");
        organizationDTO.setDescription("Test Description");
        organizationDTO.setLocation("Copenhagen");
    }

    @Test
    void canCreateOrganization() throws Exception {
        mockMvc.perform(post("/api/organizations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(organizationDTO)))
                .andExpect(status().isCreated());
    }


}
