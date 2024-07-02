package com.example.SocialPlatform.controllers;

import com.example.SocialPlatform.dtos.UserProfileDTO;
import com.example.SocialPlatform.entites.UserEntity;
import com.example.SocialPlatform.entites.UserProfileEntity;
import com.example.SocialPlatform.services.UserProfileService;
import com.example.SocialPlatform.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping("/profiles")
    public ResponseEntity<UserProfileEntity> create(@RequestBody UserProfileEntity profile) {
        return new ResponseEntity<UserProfileEntity>(userProfileService.create(profile), HttpStatus.OK);
    }

    @GetMapping("/profiles")
    public ResponseEntity<List<UserProfileEntity>> getProfiles() {
        return new ResponseEntity<>(userProfileService.getProfiles(), HttpStatus.OK);
    }

    @PostMapping("/profile/{id}")
    public ResponseEntity<UserProfileEntity> getProfile(@PathVariable UUID id) {
        return new ResponseEntity<>(userProfileService.getProfile(id), HttpStatus.OK);
    }

    @DeleteMapping("/profile/{id}")
    public ResponseEntity<UserProfileEntity> deleteProfile(@PathVariable UUID id) {
        return new ResponseEntity<>(userProfileService.deleteProfile(id), HttpStatus.OK);
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<UserProfileEntity> updateProfile(@PathVariable UUID id, @RequestBody UserProfileEntity profile) {
        return new ResponseEntity<>(userProfileService.updateProfile(id, profile), HttpStatus.OK);
    }

    @GetMapping("/profile/getFriends/{id}")
    public ResponseEntity<List<UserProfileEntity>> getFriends(@PathVariable UUID id) {
        List<UserProfileEntity> friends = userProfileService.getFriends(id);
        if (friends != null) {
            return new ResponseEntity<>(friends, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/profile/getNonFriends/{id}")
    public ResponseEntity<List<UserProfileDTO>> getNonFriends(@PathVariable UUID id) {
        List<UserProfileDTO> nonFriends = userProfileService.getNonFriends(id);
        if (nonFriends != null) {
            return new ResponseEntity<>(nonFriends, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/profile/{profileId}/addFriend/{friendProfileId}")
    public ResponseEntity<UserProfileEntity> addFriend(@PathVariable UUID profileId, @PathVariable UUID friendProfileId) {
        UserProfileEntity updatedProfile = userProfileService.addFriend(profileId, friendProfileId);
        if (updatedProfile != null) {
            return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/profile/{profileId}/removeFriend/{friendProfileId}")
    public ResponseEntity<UserProfileEntity> removeFriend(@PathVariable UUID profileId, @PathVariable UUID friendProfileId) {
        UserProfileEntity updatedProfile = userProfileService.removeFriend(profileId, friendProfileId);
        if (updatedProfile != null) {
            return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
