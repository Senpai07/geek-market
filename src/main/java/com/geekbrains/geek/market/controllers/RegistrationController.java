package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.entities.Category;
import com.geekbrains.geek.market.entities.Role;
import com.geekbrains.geek.market.entities.User;
import com.geekbrains.geek.market.entities.UserProfile;
import com.geekbrains.geek.market.exceptions.ResourceNotFoundException;
import com.geekbrains.geek.market.services.ProfileService;
import com.geekbrains.geek.market.services.RoleService;
import com.geekbrains.geek.market.services.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final RoleService roleService;
    private final ProfileService profileService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/reg")
    public void registerUser(@ModelAttribute User user) {
        Collection<Role> list = new ArrayList<>();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        list.add(new Role(1L, "ROLE_USER"));
        user.setRoles(list);
        userService.saveUser(user);
    }

    @GetMapping(produces = "application/json")
    public UserProfile getUserProfile(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find user: " + principal.getName() + ". User doesn't exist!"));
        return profileService.findById(user.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find profile by id: " + user.getId() + ". Profile doesn't exist!"));
    }

}

