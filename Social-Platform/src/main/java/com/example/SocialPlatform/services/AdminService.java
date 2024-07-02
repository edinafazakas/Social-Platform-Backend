package com.example.SocialPlatform.services;

import com.example.SocialPlatform.entites.PostEntity;
import com.example.SocialPlatform.entites.UserProfileEntity;
import com.example.SocialPlatform.repositories.PostRepository;
import com.example.SocialPlatform.repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AdminService {

    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private PostRepository postRepository;

    public UserProfileEntity validateProfile(UUID id) {
        Optional<UserProfileEntity> optionalProfile = userProfileRepository.findById(id);
        if (optionalProfile.isPresent()) {
            UserProfileEntity profile = optionalProfile.get();
            profile.setValidated(true);
            return userProfileRepository.save(profile);
        } else {
            return null;
        }
    }

    public UserProfileEntity blockProfile(UUID id) {
        UserProfileEntity profile = userProfileRepository.findById(id).orElse(null);
        if (profile != null) {
            userProfileRepository.delete(profile);
        }
        return profile;
    }

    public PostEntity blockPost(Long postId){
        PostEntity post = postRepository.findById(postId).orElse(null);
        if (post == null){
            return null;
        }
        else{
            postRepository.delete(post);
        }
        return post;
    }

    public PostEntity editPostByAdmin(PostEntity postData){
        PostEntity post = postRepository.findById(postData.getId()).orElse(null);
        if (post == null) return null;

        post.setContent(postData.getContent());
        post.setImageUrl(postData.getImageUrl());
        post.setLocation(postData.getLocation());
        post.setFriendName(postData.getFriendName());
        postRepository.save(post);

        return post;
    }


}
