package com.food.Exception;

import com.food.Dto.Response.GenericResponseBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalException
{
    @ExceptionHandler(TokenNotGiven.class)
    public ResponseEntity<String> tokenNotGiven(TokenNotGiven e)
    {
        return ResponseEntity.status(200).body(e.getMessage());
    }
    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<GenericResponseBean<?>> userNotFound(UserAlreadyExist e)
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(GenericResponseBean.builder().message(e.getMessage()).status(false).build());
    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<GenericResponseBean<?>> invalidCredentials(InvalidCredentialsException e)
    {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(GenericResponseBean.builder().message(e.getMessage()).status(false).build());
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFound(UserNotFoundException e)
    {
        return ResponseEntity.status(200).body(e.getMessage());
    }
    @ExceptionHandler(FoodOrderingMainException.class)
    public ResponseEntity<String> foodOrderingMainException(FoodOrderingMainException e)
    {
        return ResponseEntity.status(200).body(e.getMessage());
    }
    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<GenericResponseBean<?>> authenticationFailedException(AuthenticationFailedException e)
    {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(GenericResponseBean.builder().message(e.getMessage()).status(false).build());
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<GenericResponseBean<List<String>>> handleValidationException(ValidationException ex) {
        List<String> errors = ex.getErrors();
        return ResponseEntity.badRequest().body(GenericResponseBean.<List<String>>builder()
                .status(false)
                .message(ex.getMessage())
                .data(errors)
                .build());
    }
}
