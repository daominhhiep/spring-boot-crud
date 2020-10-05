package com.codegym.boot.repositories;

import com.codegym.boot.models.MyUser;
import com.codegym.boot.models.Role;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Qualifier("myUserRepository")
public interface IMyUserRepository extends CrudRepository<MyUser, Long> {
    MyUser findByUsername(String username);

    List<MyUser> findAllByRole(Role role);
}
