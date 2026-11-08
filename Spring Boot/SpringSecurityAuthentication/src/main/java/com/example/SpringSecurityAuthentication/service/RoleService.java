package com.example.SpringSecurityAuthentication.service;

import com.example.SpringSecurityAuthentication.entity.Role;
import com.example.SpringSecurityAuthentication.repository.RoleRepository;
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
