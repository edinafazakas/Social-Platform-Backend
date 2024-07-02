package com.example.SocialPlatform.repositories;

import com.example.SocialPlatform.dtos.RegisterDto;
import com.example.SocialPlatform.entites.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity deleteByUserID(UUID id);
    UserEntity findByUserID(UUID id);
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
}
