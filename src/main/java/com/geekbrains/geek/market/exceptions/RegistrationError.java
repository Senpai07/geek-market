package com.geekbrains.geek.market.exceptions;

import lombok.Data;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class RegistrationError {
    private int status;
    private List<String> message;
    private Date timestamp;

    public RegistrationError(String error) {
        this.status = HttpStatus.BAD_REQUEST.value();
        this.message = new ArrayList<>(Collections.singletonList(error));
        this.timestamp = new Date();
    }

    public RegistrationError(List<ObjectError> errors) {
        this.status = HttpStatus.BAD_REQUEST.value();
        this.message = errors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        this.timestamp = new Date();
    }
}
