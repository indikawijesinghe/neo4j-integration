package com.example.neo4jintegration.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Configuration
@EnableNeo4jRepositories(basePackages = "com.example.neo4jintegration.repository")
public class Neo4jConfig {

}

