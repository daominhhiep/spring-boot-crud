package com.codegym.boot.controller;

import com.codegym.boot.models.MyUser;
import com.codegym.boot.services.impl.MyUserService;
import com.codegym.boot.services.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private MyUserService myUserService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/admin")
    public String getAdminPage() {
        return "/admin/welcome";
    }

    @GetMapping("/admin/users")
    public ModelAndView listUsers(@SortDefault(sort = {"username"}) @PageableDefault(value = 5) Pageable pageable) {
        List<MyUser> users;
        users = myUserService.getUserList();
        ModelAndView modelAndView = new ModelAndView("admin/list-users");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/admin/users/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        MyUser user = myUserService.findById(id);
        if(user != null) {
            ModelAndView modelAndView = new ModelAndView("/admin/delete");
            modelAndView.addObject("user", user);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/admin/users/delete")
    public String deleteUser(@ModelAttribute("user") MyUser user){
        myUserService.remove(user.getId());
        return "redirect:";
    }
}
