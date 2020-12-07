package com.geekbrains.geek.market.services;

import com.geekbrains.geek.market.entities.Role;
import com.geekbrains.geek.market.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Optional<Role> findRoleByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

    public Optional<Role> getUserRole() {
        return roleRepository.findByName("ROLE_USER");
    }
}
