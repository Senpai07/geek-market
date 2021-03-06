package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.entities.Profile;
import com.geekbrains.geek.market.entities.Role;
import com.geekbrains.geek.market.entities.User;
import com.geekbrains.geek.market.exceptions.ResourceNotFoundException;
import com.geekbrains.geek.market.services.ProfileService;
import com.geekbrains.geek.market.services.RoleService;
import com.geekbrains.geek.market.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/api/v1/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final RoleService roleService;
    private final ProfileService profileService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestParam String password,
                             @RequestParam String firstname,
                             @RequestParam String surname,
                             @RequestParam String email,
                             @RequestParam String phone,
                             @RequestParam Integer birth_year,
                             @RequestParam String gender,
                             @RequestParam String city) {
        Collection<Role> list = new ArrayList<>();
        list.add(roleService.findRoleByName("ROLE_USER").orElseThrow(() ->
                new ResourceNotFoundException("Unable to find role: ROLE_USER")));
        User user = new User();
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(list);
        userService.saveUser(user);
    }
}

