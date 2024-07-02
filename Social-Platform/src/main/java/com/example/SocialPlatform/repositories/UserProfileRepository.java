package com.example.SocialPlatform.repositories;

import com.example.SocialPlatform.entites.UserEntity;
import com.example.SocialPlatform.entites.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserProfileRepository extends JpaRepository<UserProfileEntity, UUID> {
}
