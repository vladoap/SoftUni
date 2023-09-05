package com.example.mobilelele_web.model.binding;

public class UserLoginBindingDto {

    private String username;
    private String password;


    public UserLoginBindingDto() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
