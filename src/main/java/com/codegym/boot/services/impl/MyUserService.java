package com.codegym.boot.services.impl;


import com.codegym.boot.models.MyUser;
import com.codegym.boot.models.Role;
import com.codegym.boot.repositories.IMyUserRepository;
import com.codegym.boot.services.IMyUserService;
import com.codegym.boot.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyUserService implements IMyUserService, UserDetailsService {

    @Autowired
    private IMyUserRepository myUserRepository;

    @Autowired
    private IRoleService iRoleService;

    @Override
    public List<MyUser> getUserList() {
        return myUserRepository.findAllByRole(iRoleService.getRoleUser());
    }

    @Override
    public MyUser findById(Long id) {
        return myUserRepository.findById(id).orElse(null);
    }
    
    @Override
    public void save(MyUser myUser) {
        myUserRepository.save(myUser);
    }

    @Override
    public void remove(Long id) {
        myUserRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = myUserRepository.findByUsername(username);
        List<Role> myRole = new ArrayList<Role>();
        myRole.add(myUser.getRole());
        User user = new User(myUser.getUsername(), myUser.getPassword(), myRole);
        return user;
    }
}
