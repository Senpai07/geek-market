package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.entities.User;
import com.geekbrains.geek.market.entities.UserProfile;
import com.geekbrains.geek.market.exceptions.ResourceNotFoundException;
import com.geekbrains.geek.market.services.ProfileService;
import com.geekbrains.geek.market.services.RoleService;
import com.geekbrains.geek.market.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ProfileService profileService;

    @GetMapping(produces = "application/json")
    public UserProfile getUserProfile(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find user: " + principal.getName() + ". User doesn't exist!"));
        return profileService.findById(user.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find profile by id: " + user.getId() + ". Profile doesn't exist!"));
    }

}

