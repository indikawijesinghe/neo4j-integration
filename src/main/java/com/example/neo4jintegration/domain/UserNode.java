package com.example.neo4jintegration.domain;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Data
@Node("User")
public class UserNode {

    @Id
    private Long empId;

    private String name;

    @Relationship(type = "REPORTS_TO", direction = Relationship.Direction.OUTGOING)
    private List<UserNode> managers = new ArrayList<>();
}
