package com.example.SocialPlatform.services;

import com.example.SocialPlatform.entites.PostEntity;
import com.example.SocialPlatform.entites.UserProfileEntity;
import com.example.SocialPlatform.repositories.PostRepository;
import com.example.SocialPlatform.repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserProfileService userProfileService;

    public List<PostEntity> getPosts(UUID userProfileId) {
        return postRepository.findByUserProfileId(userProfileId);
    }

    public PostEntity addPost(PostEntity postData, UUID profileId){
        UserProfileEntity userProfileEntity = userProfileService.getProfile(profileId);
        postData.setUserProfile(userProfileEntity);
        postData.setCreatedAt(new Date());
        return postRepository.save(postData);
    }

    public PostEntity editPost(PostEntity postData){
        PostEntity post = postRepository.findById(postData.getId()).orElse(null);
        if (post == null) return null;

        post.setContent(postData.getContent());
        post.setImageUrl(postData.getImageUrl());
        post.setLocation(postData.getLocation());
        post.setFriendName(postData.getFriendName());
        postRepository.save(post);

        return post;
    }

    public PostEntity deletePost(Long postId){
        PostEntity post = postRepository.findById(postId).orElse(null);
        if (post == null) return null;

        postRepository.delete(post);

        return post;
    }

    public List<PostEntity> newsFeed(UUID userId) {
        List<UserProfileEntity> friendsProfiles = userProfileService.getFriends(userId);
        List<PostEntity> newsFeedList = new ArrayList<>();
        friendsProfiles.forEach((friendProfile) -> {
            newsFeedList.addAll(friendProfile.getPosts());
        });

        return newsFeedList;
    }

    public PostEntity getPost(Long postId){
        PostEntity post = postRepository.findById(postId).orElse(null);
        if (post == null) return null;
        return post;
    }

    public List<PostEntity> getAllPosts() {
        return postRepository.findAll();
    }
}
