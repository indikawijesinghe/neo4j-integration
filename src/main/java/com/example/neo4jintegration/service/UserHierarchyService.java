package com.example.neo4jintegration.service;

import com.example.neo4jintegration.domain.UserNode;
import com.example.neo4jintegration.dto.UserHierarchyDTO;
import com.example.neo4jintegration.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional
public class UserHierarchyService {

    private final UserRepository userRepository;

    public UserHierarchyService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUsers(List<UserHierarchyDTO> users){

        Map<Long, UserNode> cache = new HashMap<>();

        // 1️⃣ Create all user nodes first
        for (UserHierarchyDTO dto : users) {
            UserNode user = new UserNode();
            user.setEmpId(dto.getEmpId());
            user.setName(dto.getName());
            cache.put(dto.getEmpId(), userRepository.save(user));
        }

        // 2️⃣ Create REPORTS_TO relationships
        for(UserHierarchyDTO dto : users){
            if (dto.getManagersIds() == null || dto.getManagersIds().isEmpty()) {
                continue;
            }
            UserNode employee = cache.get(dto.getEmpId());
            List<UserNode> managers = dto.getManagersIds().stream().map(cache::get).filter(Objects::nonNull).toList();
            employee.setManagers(managers);
            userRepository.save(employee);
        }
    }

    @Transactional(readOnly = true)
    public List<UserNode> getManagers(Long empId) {
        return userRepository.findAllManagers(empId);
    }

    @Transactional(readOnly = true)
    public List<UserNode> getDirectSubordinates(Long empId) {
        return userRepository.findDirectSubordinates(empId);
    }
}
