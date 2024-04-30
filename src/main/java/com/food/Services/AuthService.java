package com.food.Services;

import com.food.Dto.Response.AuthRegisterResponseDto;
import com.food.Dto.Response.GenericResponseBean;
import com.food.Exception.AuthenticationFailedException;
import com.food.Exception.InvalidCredentialsException;
import com.food.Exception.UserAlreadyExist;
import com.food.Dto.Response.AuthResponseDto;
import com.food.Exception.ValidationException;
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
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
public class AuthService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final Validator validator;
    private final ImageService imageService;

    public ResponseEntity<GenericResponseBean<AuthResponseDto>> createUser(MultipartFile multipartFile, AuthRequestForRegisterDto authRequest)
    {
        Errors errors = new BeanPropertyBindingResult(authRequest, "authRequest");
        validator.validate(authRequest, errors);

        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }
        userRepository.findByEmail(authRequest.getEmail()).ifPresent(user -> {
            throw new UserAlreadyExist("User already exists");
        });
        String userImageUrl=imageService.upload(multipartFile);
        User saveduser=User.builder().email(authRequest.getEmail()).
                address(authRequest.getAddress()).
                password(passwordEncoder.encode(authRequest.getPassword())).
                name(authRequest.getName()).
                imageUrl(userImageUrl).
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
