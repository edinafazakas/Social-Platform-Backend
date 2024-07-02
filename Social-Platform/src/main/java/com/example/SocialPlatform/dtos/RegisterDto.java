package com.example.SocialPlatform.dtos;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RegisterDto {

    public String name;
    public String role;
    public String email;
    public String username;
    public String password;
}
