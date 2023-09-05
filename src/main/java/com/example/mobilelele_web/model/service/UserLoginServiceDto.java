package com.example.mobilelele_web.model.service;

public class UserLoginServiceDto {

    private String username;
    private String rawPassword;

    public UserLoginServiceDto() {
    }

    public String getUsername() {
        return username;
    }

    public UserLoginServiceDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public UserLoginServiceDto setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
        return this;
    }
}
