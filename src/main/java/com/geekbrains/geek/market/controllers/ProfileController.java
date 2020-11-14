package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.dto.ProfileDto;
import com.geekbrains.geek.market.entities.Category;
import com.geekbrains.geek.market.entities.Profile;
import com.geekbrains.geek.market.entities.User;
import com.geekbrains.geek.market.exceptions.MarketError;
import com.geekbrains.geek.market.exceptions.ResourceNotFoundException;
import com.geekbrains.geek.market.services.CategoryService;
import com.geekbrains.geek.market.services.ProfileService;
import com.geekbrains.geek.market.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(produces = "application/json")
    public ProfileDto getCurrentProfile(Principal principal) {
        Profile p = profileService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to find profile for current user"));
        return new ProfileDto(p);
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<?> getCurrentProfile(Principal principal, @RequestBody ProfileDto profileDto) {
        User currentUser = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to find current user"));
        if (profileDto.getConfirmationPassword() == null || !passwordEncoder.matches(profileDto.getConfirmationPassword(), currentUser.getPassword())) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Incorrect password"), HttpStatus.BAD_REQUEST);
        }
        Profile p = profileService.findById(profileDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Unable to find profile for current user"));
        p.setHobbies(profileDto.getHobbies());
        profileService.saveOrUpdate(p);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
