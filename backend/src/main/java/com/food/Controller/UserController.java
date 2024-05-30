package com.food.Controller;

import com.food.Dto.Response.AuthResponseDto;
import com.food.Dto.Response.GenericResponseBean;
import com.food.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController
{
    private final UserService userService;
    @PostMapping("/upload/image")
    public ResponseEntity<GenericResponseBean<AuthResponseDto>> uploadUserImage(@RequestParam("file")MultipartFile multipartFile, @RequestParam("user_email") String userEmail)
    {
        return userService.uploadUserImage(multipartFile,userEmail);
    }
}
