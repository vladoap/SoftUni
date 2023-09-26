package com.example.pathfinder.model.binding;

import com.example.pathfinder.model.entity.UserRole;
import com.example.pathfinder.model.enums.LevelEnum;
import com.example.pathfinder.validator.MatchingPassword;
import jakarta.validation.constraints.*;

import java.util.Set;

@MatchingPassword(message = "Passwords are not the same.")
public class UserRegisterBindingModel {

    private Integer age;
    private String fullName;
    private String email;
    private String password;
    private String confirmPassword;
    private String username;

    @Positive(message = "Enter positive number for age.")
    public Integer getAge() {
        return age;
    }

    public UserRegisterBindingModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    @NotBlank
    @Size(min = 5, message = "Enter valid full name minimum 5 symbols.")
    public String getFullName() {
        return fullName;
    }

    public UserRegisterBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @NotBlank
    @Email(message = "Enter valid email.")
    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    @NotNull
    @Size(min = 2,  message = "Password must be at least 2 symbols.")
    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }
    @NotNull
    @Size(min = 2,  message = "Password must be at least 2 symbols.")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
    @NotNull
    @Size(min = 3, message = "Username must be at least 3 symbols.")
    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }
}
