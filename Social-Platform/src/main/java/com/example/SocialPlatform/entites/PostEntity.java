package com.example.SocialPlatform.entites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="posts")
@Data
@JsonIgnoreProperties("userProfile")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfileEntity userProfile;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "content")
    private String content;

    @Column(name = "location")
    private String location;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "friend_name")
    private String friendName;

    public PostEntity(String location, String imageUrl, UserProfileEntity userProfile, Date createdAt, String content, String friendName) {
        this.userProfile = userProfile;
        this.createdAt = createdAt;
        this.content = content;
        this.location = location;
        this.imageUrl = imageUrl;
        this.friendName = friendName;
    }

    public PostEntity() {

    }
}
