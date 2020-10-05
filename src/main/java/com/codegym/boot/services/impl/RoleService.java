package com.codegym.boot.services.impl;

import com.codegym.boot.models.Role;
import com.codegym.boot.repositories.IRoleRepository;
import com.codegym.boot.services.IRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleUser(){
        return roleRepository.findById(1L).orElse(null);
    }
}
