package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.entities.Role;
import com.geekbrains.geek.market.entities.User;
import com.geekbrains.geek.market.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {

    private UserService userService;

    @GetMapping
    public String showCartPage() {
        return "registration";
    }

    @PostMapping("/confirm")
    public String saveProduct(@ModelAttribute User user) {
        Collection<Role> list = new ArrayList<Role>();
        list.add(new Role(1l, "ROLE_USER"));
        user.setRoles(list);
        userService.saveUser(user);
        return "redirect:/products";
    }

}

