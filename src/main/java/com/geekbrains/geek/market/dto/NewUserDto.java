package com.geekbrains.geek.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class NewUserDto {
    @Size(min = 4, message = "Username length at least 4 characters")
    private String username;

    @Size(min = 6, message = "Password length at least 6 characters")
    private String password;

    private String confirmPassword;

    @Email(message = "E-mail format error")
    private String email;

}