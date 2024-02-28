package com.food.Services;

import com.food.Entities.Role;
import com.food.Entities.User;
import com.food.Repository.UserRepository;
import com.food.Utils.AuthRequestForLogin;
import com.food.Utils.AuthRequestForRegister;
import com.food.Utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    //create user
    public User createUser(AuthRequestForRegister authRequest)
    {
//        if(userRepository.findByEmail(authRequest.getEmail()).isPresent())
//        {
//            throw new RuntimeException("User already exists");
//        }
        User saveduser=User.builder().email(authRequest.getEmail()).
                address(authRequest.getAddress()).
                password(passwordEncoder.encode(authRequest.getPassword())).
                role(Role.ROLE_ADMIN).
                build();
        userRepository.save(saveduser);
        return saveduser;

    }

    public User login(AuthRequestForLogin authRequest)
    {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if(authenticate.isAuthenticated())
        {
            return userRepository.findByEmail(authRequest.getEmail()).orElseThrow(()->new RuntimeException("User not found"));
        }
        return userRepository.findByEmail(authRequest.getEmail()).orElseThrow(()->new RuntimeException("User not found"));
    }
}
