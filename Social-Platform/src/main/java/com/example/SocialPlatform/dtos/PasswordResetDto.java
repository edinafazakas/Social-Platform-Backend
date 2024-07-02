package com.example.SocialPlatform.dtos;

public class PasswordResetDto {

    private String email;
    private String newPassword;

    public PasswordResetDto() {
    }

    public PasswordResetDto(String email, String newPassword) {
        this.email = email;
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
