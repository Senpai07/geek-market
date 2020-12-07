package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.dto.NewUserDto;
import com.geekbrains.geek.market.exceptions.RegistrationError;
import com.geekbrains.geek.market.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/v1/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    //    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Validated NewUserDto newUserDto, BindingResult bindingResult) {
        if (userService.findByUsername(newUserDto.getUsername()).isPresent()) {
            return new ResponseEntity<>(new RegistrationError(
                    "Username " + newUserDto.getUsername() + " is present"), HttpStatus.BAD_REQUEST);
        }
        if (!newUserDto.getPassword().equals(newUserDto.getConfirmPassword())) {
            return new ResponseEntity<>(new RegistrationError("Passwords not equals"), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new RegistrationError(bindingResult.getAllErrors()), HttpStatus.BAD_REQUEST);
        }

        userService.createUserFromDto(newUserDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void registerUser(@RequestParam String password,
//                             @RequestParam String firstname,
//                             @RequestParam String surname,
//                             @RequestParam String email,
//                             @RequestParam String phone,
//                             @RequestParam Integer birth_year,
//                             @RequestParam String gender,
//                             @RequestParam String city) {
//        Collection<Role> list = new ArrayList<>();
//        list.add(roleService.findRoleByName("ROLE_USER").orElseThrow(() ->
//                new ResourceNotFoundException("Unable to find role: ROLE_USER")));
//        User user = new User();
//        user.setPassword(passwordEncoder.encode(password));
//        user.setRoles(list);
//        userService.saveUser(user);
//    }
}

