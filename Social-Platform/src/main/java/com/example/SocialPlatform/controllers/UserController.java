package com.example.SocialPlatform.controllers;

import com.example.SocialPlatform.dtos.PasswordResetDto;
import com.example.SocialPlatform.dtos.RegisterDto;
import com.example.SocialPlatform.entites.UserEntity;
import com.example.SocialPlatform.services.UserProfileService;
import com.example.SocialPlatform.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.regex.Pattern;


@CrossOrigin
@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserProfileService userProfileService;


    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,4}$");

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        if (!isValidEmail(email)) {
            return ResponseEntity.badRequest().body("Invalid email format");
        }

        if (email.isEmpty() || password.isEmpty()) {
            return ResponseEntity.badRequest().body("Email and password fields must not be empty");
        }

        UserEntity user = userService.findUserByEmail(email);

        if (user != null) {
                if ( user.getEmail().equals(email) && user.getPassword().equals(password)){
                    var userProfileId = userProfileService.getProfiles().stream().filter(u -> u.user != null && u.user.userID.equals(user.userID)).findFirst().get();
                    return ResponseEntity.ok(userProfileId.getId());
                } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@RequestBody RegisterDto registerDto) {
        UserEntity userEntity = new UserEntity(registerDto.getName(), registerDto.getRole(), registerDto.getEmail(),
                registerDto.getUsername(), registerDto.getPassword());
        return new ResponseEntity<>(userService.create(userEntity), HttpStatus.OK);
    }

    @PostMapping("/passwordReset")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetDto resetDto) {
        String email = resetDto.getEmail();
        String newPassword = resetDto.getNewPassword();

        if (!isValidEmail(email)) {
            return ResponseEntity.badRequest().body("Invalid email format!");
        }

        UserEntity user = userService.findUserByEmail(email);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }

        user.setPassword(newPassword);
        userService.updateUser(user.getUserID(), user);

        return ResponseEntity.ok("Password reset successfully!");
    }
}
