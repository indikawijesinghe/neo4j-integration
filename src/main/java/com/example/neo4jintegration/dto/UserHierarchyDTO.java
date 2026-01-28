package com.example.neo4jintegration.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserHierarchyDTO {

    private Long empId;
    private String name;
    private List<Long> managersIds;

    public UserHierarchyDTO(Long empId, String name, List<Long> managersIds) {
        this.empId = empId;
        this.name = name;
        this.managersIds = managersIds;
    }
}
