package com.food.Oauth;

import com.food.Dto.Response.GenericResponseBean;
import com.food.Utils.Role;
import com.food.Entities.User;
import com.food.Repository.UserRepository;
import com.food.Dto.Request.GoogleSignUpRequestDto;
import com.food.Dto.Response.GoogleSignUpResponseDto;
import com.food.Services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GoogleAuth
{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public ResponseEntity<GenericResponseBean<GoogleSignUpResponseDto>> googleSignUp(GoogleSignUpRequestDto googleSignUpRequestDto)
    {
        String token = null;
        Optional<User> userOpt = userRepository.findByEmail(googleSignUpRequestDto.getEmail());
        if(userOpt.isPresent()){
            token= jwtService.generateToken(userOpt.get().getEmail());
            GoogleSignUpResponseDto googleSignUpResponseDto = GoogleSignUpResponseDto.builder().token(token).build();
            return ResponseEntity.status(HttpStatus.OK).body(
                    GenericResponseBean.<GoogleSignUpResponseDto>builder().
                            data(googleSignUpResponseDto).message("Successfully Signup").status(true).build());
        }
        User user=new User();
        user.setRole(Role.ROLE_USER);
        user.setEmail(googleSignUpRequestDto.getEmail());
        String pass= UUID.randomUUID().toString();
        String encode = passwordEncoder.encode(pass);
        user.setPassword(encode);
        user.setName(googleSignUpRequestDto.getName());
        user.setImageUrl(googleSignUpRequestDto.getImageUrl());
        userRepository.save(user);
        token= jwtService.generateToken(user.getEmail());
        GoogleSignUpResponseDto googleSignUpResponseDto = GoogleSignUpResponseDto.builder().token(token).build();
        return ResponseEntity.status(HttpStatus.OK).body(
                GenericResponseBean.<GoogleSignUpResponseDto>builder().
                        data(googleSignUpResponseDto)
                        .message("Successfully Signup")
                        .status(true)
                        .build());
    }

}
