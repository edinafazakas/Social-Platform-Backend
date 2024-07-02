package com.example.SocialPlatform.controllers;

import com.example.SocialPlatform.entites.PostEntity;
import com.example.SocialPlatform.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@CrossOrigin
@RestController
@RequiredArgsConstructor
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts/{userProfileId}")
    public ResponseEntity<?> getPosts(@PathVariable UUID userProfileId) {
        List<PostEntity> posts = postService.getPosts(userProfileId);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping("/posts/{profileId}")
    public ResponseEntity<?> addPost(@RequestBody PostEntity postData, @PathVariable UUID profileId) {
        PostEntity post = postService.addPost(postData, profileId);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        PostEntity post = postService.deletePost(postId);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping("/posts")
    public ResponseEntity<?> editPost(@RequestBody PostEntity postData) {
        PostEntity post = postService.editPost(postData);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/news-feed/{userId}")
    public ResponseEntity<?> newsFeed(@PathVariable UUID userId){
        List<PostEntity> newsFeed = postService.newsFeed(userId);

        return new ResponseEntity<>(newsFeed, HttpStatus.OK);
    }

    @GetMapping("/posts/getPost/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Long postId){
        var post = postService.getPost(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/getAllPosts")
    public ResponseEntity<?> getPosts() {
        List<PostEntity> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
