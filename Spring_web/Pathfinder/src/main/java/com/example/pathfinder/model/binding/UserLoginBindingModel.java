package com.example.pathfinder.model.binding;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserLoginBindingModel {

    private String username;
    private String rawPassword;

    @NotNull
    @Size(min = 3, message = "Username must be at least 3 symbols.")
    public String getUsername() {
        return username;
    }

    public UserLoginBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @NotNull
    @Size(min = 2,  message = "Password must be at least 2 symbols.")
    public String getRawPassword() {
        return rawPassword;
    }

    public UserLoginBindingModel setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
        return this;
    }
}
