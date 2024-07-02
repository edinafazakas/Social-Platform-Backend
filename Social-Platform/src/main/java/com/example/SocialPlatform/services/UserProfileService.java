package com.example.SocialPlatform.services;

import com.example.SocialPlatform.dtos.UserProfileDTO;
import com.example.SocialPlatform.entites.UserEntity;
import com.example.SocialPlatform.entites.UserProfileEntity;
import com.example.SocialPlatform.repositories.UserProfileRepository;
import com.example.SocialPlatform.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfileEntity create(UserProfileEntity profile) {
        return (userProfileRepository.save(profile));
    }


    public List<UserProfileEntity> getProfiles() {
        return userProfileRepository.findAll();
    }

    public UserProfileEntity deleteProfile(UUID id) {
        UserProfileEntity profile = userProfileRepository.findById(id).orElse(null);
        if (profile == null) return null;

        userProfileRepository.delete(profile);

        return profile;
    }

    public UserProfileEntity getProfile(UUID id) {
        return userProfileRepository.findById(id).orElse(null);
    }

    public UserProfileEntity updateProfile(UUID id, UserProfileEntity profile) {
        UserProfileEntity fetchedProfile = userProfileRepository.findById(id).orElse(null);
        fetchedProfile.setDescription(profile.getDescription());
        fetchedProfile.setName(profile.getName());
        fetchedProfile.setPicture(profile.getPicture());
        if (fetchedProfile == null)
            return null;
        return userProfileRepository.save(fetchedProfile);
    }

    public List<UserProfileEntity> getFriends(UUID id) {
        UserProfileEntity userProfileEntity = userProfileRepository.findById(id).orElse(null);
        if (userProfileEntity != null) {
            return new ArrayList<>(userProfileEntity.getFriends());
        }
        return null;
    }

    public List<UserProfileDTO> getNonFriends(UUID id) {
        UserProfileEntity userProfileEntity = userProfileRepository.findById(id).orElse(null);
        List<UserProfileDTO> nonFriends = new ArrayList<>();
        List<UserProfileEntity> allProfiles = userProfileRepository.findAll();

        if (userProfileEntity != null) {
            allProfiles.removeAll(userProfileEntity.getFriends());
        }

        for (UserProfileEntity friend : userProfileEntity.getFriends()) {
            Iterator<UserProfileEntity> iterator = allProfiles.iterator();
            while (iterator.hasNext()) {
                UserProfileEntity profile = iterator.next();
                if (profile.getId().equals(friend.getId())) {
                    iterator.remove();
                    break;
                }
            }
        }

        for (UserProfileEntity u : allProfiles) {
            System.out.println(u.getId() + " " + id);
            if (!u.getId().equals(id)) {
                UserProfileDTO userProfileDTO = new UserProfileDTO();
                userProfileDTO.setUserProfileID(u.getId());
                userProfileDTO.setDescription(u.getDescription());
                userProfileDTO.setName(u.getName());
                userProfileDTO.setPicture(u.getPicture());
                nonFriends.add(userProfileDTO);
            }

        }
        return nonFriends;
    }


    public UserProfileEntity addFriend(UUID userProfileId, UUID friendProfileId) {
        UserProfileEntity userProfileEntity = userProfileRepository.findById(userProfileId).orElse(null);
        UserProfileEntity friendProfileEntity = userProfileRepository.findById(friendProfileId).orElse(null);

        if (userProfileEntity != null && friendProfileEntity != null) {
            userProfileEntity.addFriend(friendProfileEntity);
            return userProfileRepository.save(userProfileEntity);
        }
        return null;
    }


    public UserProfileEntity removeFriend(UUID userProfileId, UUID friendProfileId) {
        UserProfileEntity userProfileEntity = userProfileRepository.findById(userProfileId).orElse(null);
        UserProfileEntity friendProfileEntity = userProfileRepository.findById(friendProfileId).orElse(null);

        if (userProfileEntity != null && friendProfileEntity != null) {
            userProfileEntity.getFriends().removeIf(f -> f.getId().equals(friendProfileId));
            friendProfileEntity.getFriends().removeIf(f -> f.getId().equals(userProfileId));

            friendProfileEntity.getFriends().removeIf(f -> f.getId().equals(userProfileEntity.getId()));

            userProfileRepository.save(userProfileEntity);
            userProfileRepository.save(friendProfileEntity);


            return userProfileEntity;
        }
        return null;
    }


}
