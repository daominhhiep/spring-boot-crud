package com.codegym.boot.services;


import com.codegym.boot.models.Role;

public interface IRoleService {
    Iterable<Role> findAll();

    Role getRoleUser();
}
