package com.example.SocialPlatform.dtos;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserProfileDTO {

    private UUID userProfileID;
    private String description;
    private String picture;
    private String name;

}
