package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.entities.Role;
import com.geekbrains.geek.market.entities.User;
import com.geekbrains.geek.market.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String showCartPage() {
        return "registration";
    }

    @PostMapping("/confirm")
    public String confirmUser(@ModelAttribute User user) {
        Collection<Role> list = new ArrayList<>();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        list.add(new Role(1L, "ROLE_USER"));
        user.setRoles(list);
        userService.saveUser(user);
        return "redirect:/products";
    }

}

