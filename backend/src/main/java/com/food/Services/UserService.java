package com.food.Services;

import com.food.Dto.Response.AuthResponseDto;
import com.food.Dto.Response.GenericResponseBean;
import com.food.Entities.User;
import com.food.Exception.UserNotFoundException;
import com.food.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository userRepository;
    private final ImageService imageService;

    public ResponseEntity<GenericResponseBean<AuthResponseDto>> uploadUserImage(MultipartFile multipartFile, String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new UserNotFoundException("User Not Found With this email"));
        String urlImage=imageService.upload(multipartFile);
        user.setImageUrl(urlImage);
        User saved = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(
                GenericResponseBean.<AuthResponseDto>builder().message("Successfully Uploaded")
                        .status(true).data(AuthResponseDto.builder().imageUrl(saved.getImageUrl()).build()).build()
        );
    }
}
