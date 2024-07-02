package com.example.SocialPlatform.repositories;

import com.example.SocialPlatform.entites.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM posts where user_profile_id = ?1")
    List<PostEntity> findByUserProfileId(UUID userProfileId);
}
