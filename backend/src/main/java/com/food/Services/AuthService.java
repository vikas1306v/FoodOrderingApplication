package com.food.Services;

import com.food.Dto.Response.AuthRegisterResponseDto;
import com.food.Dto.Response.GenericResponseBean;
import com.food.Exception.AuthenticationFailedException;
import com.food.Exception.InvalidCredentialsException;
import com.food.Exception.UserAlreadyExist;
import com.food.Dto.Response.AuthResponseDto;
import com.food.Utils.Role;
import com.food.Entities.User;
import com.food.Repository.UserRepository;
import com.food.Dto.Request.AuthRequestForLoginDto;
import com.food.Dto.Request.AuthRequestForRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;



    public ResponseEntity<GenericResponseBean<AuthResponseDto>> createUser(AuthRequestForRegisterDto authRequest)
    {
        validateAuthRequest(authRequest);
        userRepository.findByEmail(authRequest.getEmail()).ifPresent(user -> {
            throw new UserAlreadyExist("User already exists");
        });
        User saveduser=User.builder().email(authRequest.getEmail()).
                address(authRequest.getAddress()).
                password(passwordEncoder.encode(authRequest.getPassword())).
                name(authRequest.getName()).
                role(Role.ROLE_ADMIN).
                build();
        User user = userRepository.save(saveduser);
        String token = jwtService.generateToken(user.getEmail());
        AuthRegisterResponseDto.builder().token(token).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(GenericResponseBean.<AuthResponseDto>builder().
                message("User created successfully").status(true).
                data(AuthResponseDto.builder().token(token).build())
                .build());
    }
    private void validateAuthRequest(AuthRequestForRegisterDto authRequest) {
        if (authRequest.getEmail() == null || !authRequest.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (authRequest.getPassword() == null || authRequest.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }
        if (!isPasswordComplex(authRequest.getPassword())) {
            throw new IllegalArgumentException("Password must contain at least one digit, one lowercase letter, one uppercase letter, one special character");
        }
    }

    private boolean isPasswordComplex(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$");
    }


    public ResponseEntity<GenericResponseBean<AuthResponseDto>> login(AuthRequestForLoginDto authRequest) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            if (authenticate.isAuthenticated()) {
                String token = jwtService.generateToken(authRequest.getEmail());
                User user = userRepository.findByEmail(authRequest.getEmail()).get();
                return ResponseEntity.status(HttpStatus.OK).body(GenericResponseBean.<AuthResponseDto>builder()
                        .data(AuthResponseDto.builder().token(token).email(user.getEmail()).userId(user.getId()).imageUrl(user.getImageUrl())
                                .name(user.getName())
                                .build()).status(true).message("Login Successful")
                        .build());
            }
        } catch (AuthenticationException e) {
            throw new InvalidCredentialsException("Invalid Credentials");
        }
        throw new AuthenticationFailedException("Authentication failed for an unknown reason");
    }
}
