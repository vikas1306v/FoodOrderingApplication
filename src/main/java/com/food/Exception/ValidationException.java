package com.food.Exception;

import lombok.Getter;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ValidationException extends RuntimeException {

    private final List<String> errors;

    public ValidationException(Errors errors) {
        super("Validation failed");
        this.errors = errors.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    }

}
