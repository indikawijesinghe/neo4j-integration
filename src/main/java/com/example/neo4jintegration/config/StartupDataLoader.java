package com.example.neo4jintegration.config;

import com.example.neo4jintegration.dto.UserHierarchyDTO;
import com.example.neo4jintegration.repository.UserRepository;
import com.example.neo4jintegration.service.UserHierarchyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class StartupDataLoader {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, UserHierarchyService userHierarchyService) {
        return args -> {

            userRepository.deleteAll();

            List<UserHierarchyDTO> users = List.of(
                    new UserHierarchyDTO(1L, "CEO", null),
                    new UserHierarchyDTO(2L, "CTO", Collections.singletonList(1L)),
                    new UserHierarchyDTO(3L, "Dev Lead", Collections.singletonList(2L)),
                    new UserHierarchyDTO(4L, "Dev Lead 2", Collections.singletonList(2L)),
                    new UserHierarchyDTO(5L, "kasun", Collections.singletonList(3L)),
                    new UserHierarchyDTO(6L, "saman", Collections.singletonList(3L)),
                    new UserHierarchyDTO(7L, "supun", Collections.singletonList(3L)),
                    new UserHierarchyDTO(8L, "malith", Collections.singletonList(4L)),
                    new UserHierarchyDTO(9L, "damith", Arrays.asList(3L, 4L))
            );
            userHierarchyService.saveUsers(users);
        };
    }
}
