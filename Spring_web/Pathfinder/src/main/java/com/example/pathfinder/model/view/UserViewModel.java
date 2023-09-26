package com.example.pathfinder.model.view;

import com.example.pathfinder.model.enums.LevelEnum;

public class UserViewModel {

    private Integer age;
    private String fullName;
    private LevelEnum level;
    private String username;

    public Integer getAge() {
        return age;
    }

    public UserViewModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserViewModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public UserViewModel setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }
}
