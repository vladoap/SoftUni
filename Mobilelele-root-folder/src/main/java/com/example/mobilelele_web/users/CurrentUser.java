package com.example.mobilelele_web.users;

import com.example.mobilelele_web.model.entity.UserRole;
import com.example.mobilelele_web.model.enums.Role;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashSet;
import java.util.Set;

@Component
@SessionScope
public class CurrentUser {

    private boolean isLoggedIn;
    private String username;
    private String firstName;
    private String lastName;
    private Set<Role> roles;

    public CurrentUser() {
        roles = new HashSet<>();
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public CurrentUser setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CurrentUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CurrentUser setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CurrentUser setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void clean() {
        setLoggedIn(false)
                .setUsername(null)
                .setFirstName(null)
                .setLastName(null)
                .clearRoles();
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public CurrentUser addRole(Role role) {
        roles.add(role);
        return this;
    }

    public boolean isAdmin() {
        return roles.contains(Role.Admin);
    }

    public CurrentUser clearRoles() {
        roles.clear();
        return this;
    }
}
