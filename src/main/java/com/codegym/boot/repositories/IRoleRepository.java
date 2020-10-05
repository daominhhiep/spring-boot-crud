package com.codegym.boot.repositories;

import com.codegym.boot.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface IRoleRepository extends CrudRepository<Role, Long> {
}
