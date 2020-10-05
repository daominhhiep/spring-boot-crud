package com.codegym.boot.services;


import com.codegym.boot.models.MyUser;

import java.util.List;
import java.util.Optional;

public interface IMyUserService {

    List<MyUser> getUserList();

    void save(MyUser myUser);

    MyUser findById(Long id);

    void remove(Long id);

}
