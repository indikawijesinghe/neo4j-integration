package com.example.neo4jintegration.repository;

import com.example.neo4jintegration.domain.UserNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface UserRepository extends Neo4jRepository<UserNode, Long> {

    @Query("""
      MATCH (u:User {empId: $empId})-[:REPORTS_TO*1..]->(m)
      RETURN DISTINCT m
    """)
    List<UserNode> findAllManagers(Long empId);

    @Query("""
      MATCH (m:User {empId: $empId})<-[:REPORTS_TO]-(s)
      RETURN s
    """)
    List<UserNode> findDirectSubordinates(Long empId);
}
