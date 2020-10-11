package com.geekbrains.geek.market.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {

    @GetMapping
    public String showCartPage() {
        return "registration";
    }

}

