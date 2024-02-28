package com.food.Controller;


import com.food.Entities.User;
import com.food.Services.AuthService;
import com.food.Utils.AuthRequestForLogin;
import com.food.Utils.AuthRequestForRegister;
import com.food.Utils.AuthResponse;
import com.food.Utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController
{
    private final JwtService jwtService;
    private final AuthService authService;
    //register the user and get token
    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequestForRegister authRequest)
    {

        User user = authService.createUser(authRequest);
        if(user!=null)
        {
            String token = jwtService.generateToken(user.getEmail());
            return AuthResponse.builder().token(token).build();
        }
        return null;
    }

    //login the user and get token
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequestForLogin authRequest)
    {
        User user = authService.login(authRequest);
        if(user==null)
        {
            throw new RuntimeException("User not found");
        }
        String token = jwtService.generateToken(user.getEmail());
        return AuthResponse.builder().token(token).build();


    }

}
