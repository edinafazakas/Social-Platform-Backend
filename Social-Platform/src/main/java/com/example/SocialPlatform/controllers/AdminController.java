package com.example.SocialPlatform.controllers;

import com.example.SocialPlatform.entites.PostEntity;
import com.example.SocialPlatform.entites.UserProfileEntity;
import com.example.SocialPlatform.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/admin/profiles/validateProfile/{id}")
    public ResponseEntity<UserProfileEntity> validateProfile(@PathVariable UUID id) {
        UserProfileEntity validatedProfile = adminService.validateProfile(id);
        if (validatedProfile != null) {
            return ResponseEntity.ok(validatedProfile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/admin/profiles/blockProfile/{id}")
    public ResponseEntity<UserProfileEntity> blockProfile(@PathVariable UUID id) {
        UserProfileEntity blockedProfile = adminService.blockProfile(id);
        if (blockedProfile != null) {
            return ResponseEntity.ok(blockedProfile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/admin/posts/blockPost/{postId}")
    public ResponseEntity<PostEntity> blockPost(@PathVariable Long postId) {
        PostEntity blockedPost = adminService.blockPost(postId);
        if (blockedPost != null) {
            return ResponseEntity.ok(blockedPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/admin/posts/editPost")
    public ResponseEntity<PostEntity> editPostByAdmin(@RequestBody PostEntity postData) {
        PostEntity editedPost = adminService.editPostByAdmin(postData);
        if (editedPost != null) {
            return ResponseEntity.ok(editedPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
