package com.example.SocialPlatform.entites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;

@Entity
@Data
@Getter
@Setter
@Table(name = "user_profile")
public class UserProfileEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "picture", nullable = false)
    private String picture;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "validated", nullable = false)
    private boolean validated;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "userid")
    public UserEntity user;

    @JsonIgnoreProperties("friends")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_friends",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private List<UserProfileEntity> friends = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userProfile")
    public List<PostEntity> posts;

    public List<UserProfileEntity> getFriends() {
        return friends;
    }


    public void addFriend(UserProfileEntity friend) {
        this.friends.add(friend);
        friend.getFriends().add(this);
    }

    public void removeFriend(UserProfileEntity friend) {
        this.friends.remove(friend);
        friend.getFriends().remove(this);
    }
}
