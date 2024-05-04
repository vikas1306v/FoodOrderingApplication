package com.food.Controller;

import com.food.Dto.Response.GenericResponseBean;
import com.food.Oauth.GoogleAuth;
import com.food.Dto.Request.GoogleSignUpRequestDto;
import com.food.Dto.Response.GoogleSignUpResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/google")
@CrossOrigin(origins = "http://localhost:5173")
public class GoogleAuthProviderController
{
    private final GoogleAuth googleAuth;
    @PostMapping("/signup")
    public ResponseEntity<GenericResponseBean<GoogleSignUpResponseDto>> googleSignUp(@RequestBody GoogleSignUpRequestDto googleSignUpRequestDto)
    {
       return  googleAuth.googleSignUp(googleSignUpRequestDto);

    }
}
