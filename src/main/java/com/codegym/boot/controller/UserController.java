package com.codegym.boot.controller;

import com.codegym.boot.models.MyUser;
import com.codegym.boot.models.Role;
import com.codegym.boot.services.impl.MyUserService;
import com.codegym.boot.services.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    private MyUserService myUserService;

    @Autowired
    private RoleService roleService;

    @ModelAttribute("roles")
    public Iterable<Role> provinces() {
        return roleService.findAll();
    }

    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }


    @GetMapping(value = "/user")
    public String Homepage(Model model){
        model.addAttribute("user", getPrincipal());
        return "user/welcome";
    }

    @GetMapping("/create")
    public ModelAndView showCreatForm() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("user", new MyUser());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveUser(@ModelAttribute("user") MyUser myUser) {
        myUser.setRole(roleService.getRoleUser());
        myUserService.save(myUser);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("user", new MyUser());
        modelAndView.addObject("message", "New user created successfully");
        return modelAndView;
    }
}
