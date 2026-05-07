package com.springboot.organization.payload;

import lombok.Data;

@Data
public class OrganizationDTO {
    private long id;
    private String name;
    private String description;
    private String location;
}
