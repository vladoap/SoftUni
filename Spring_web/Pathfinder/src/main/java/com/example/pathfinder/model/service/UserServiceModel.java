package com.example.pathfinder.model.service;

import com.example.pathfinder.model.entity.UserRole;
import com.example.pathfinder.model.enums.LevelEnum;

import java.util.Set;

public class UserServiceModel {

    private Long id;
    private Integer age;
    private String fullName;
    private String email;
    private LevelEnum level;
    private String password;
    private String username;
    private Set<UserRole> roles;


    public Long getId() {
        return id;
    }

    public UserServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserServiceModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserServiceModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public UserServiceModel setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public UserServiceModel setRoles(Set<UserRole> roles) {
        this.roles = roles;
        return this;
    }
}
