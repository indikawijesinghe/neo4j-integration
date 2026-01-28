package com.example.neo4jintegration.controller;

import com.example.neo4jintegration.domain.UserNode;
import com.example.neo4jintegration.service.UserHierarchyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hierarchy")
public class UserController {

    private final UserHierarchyService service;

    public UserController(UserHierarchyService service) {
        this.service = service;
    }

    @GetMapping("/{empId}/managers")
    public List<UserNode> managers(@PathVariable Long empId) {
        return service.getManagers(empId);
    }

    @GetMapping("/{empId}/subordinates")
    public List<UserNode> subordinates(@PathVariable Long empId) {
        return service.getDirectSubordinates(empId);
    }
}
