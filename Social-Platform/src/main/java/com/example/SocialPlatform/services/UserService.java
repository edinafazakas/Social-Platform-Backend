package com.example.SocialPlatform.services;

import com.example.SocialPlatform.entites.UserEntity;
import com.example.SocialPlatform.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity create(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    public List<UserEntity> getUsers(){
        List<UserEntity> userEntities = new ArrayList<>();
        for(UserEntity u: userRepository.findAll()){
            if(!u.getRole().equals("admin")){
                userEntities.add(u);
            }
        }
        return userEntities;
    }

    public UserEntity deleteUser(UUID id){
        UserEntity userEntity = userRepository.findByUserID(id);
        userRepository.delete(userEntity);
        return userEntity;
    }

    public UserEntity findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public UUID getIDBYUsername(String username){
        UserEntity userEntity = userRepository.findByUsername(username);
        return userEntity.getUserID();
    }

    public UserEntity updateUser(UUID id, UserEntity updatedUser) {
        UserEntity userEntity = userRepository.findByUserID(id);

        if (userEntity != null) {
            userEntity.setName(updatedUser.getName());
            userEntity.setRole(updatedUser.getRole());
            userEntity.setEmail(updatedUser.getEmail());
            userEntity.setUsername(updatedUser.getUsername());
            userEntity.setPassword(updatedUser.getPassword());
            userRepository.save(userEntity);
        }

        return userEntity;
    }
}
