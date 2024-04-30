package com.food.Controller;


import com.food.Dto.Response.GenericResponseBean;
import com.food.Services.AuthService;
import com.food.Dto.Request.AuthRequestForLoginDto;
import com.food.Dto.Request.AuthRequestForRegisterDto;
import com.food.Dto.Response.AuthResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController
{
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<GenericResponseBean<AuthResponseDto>> register(@RequestParam("file")MultipartFile multipartFile, @Valid @RequestBody AuthRequestForRegisterDto authRequest)
    {
        return authService.createUser(multipartFile,authRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<GenericResponseBean<AuthResponseDto>> login(@RequestBody AuthRequestForLoginDto authRequest)
    {
        return authService.login(authRequest);

    }

}
