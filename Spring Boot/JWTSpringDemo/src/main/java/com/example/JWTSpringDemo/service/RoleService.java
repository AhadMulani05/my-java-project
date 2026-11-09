package com.example.JWTSpringDemo.service;

import com.example.JWTSpringDemo.entity.Role;
import com.example.JWTSpringDemo.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void addRole(Role role) {
        roleRepository.save(role);
    }

}
