package com.example.pathfinder.util;

import com.example.pathfinder.model.entity.UserRole;
import com.example.pathfinder.model.enums.UserRoleEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashSet;
import java.util.Set;

@Component
@SessionScope
public class CurrentUser {

  private Long id;
  private String username;
  private Set<UserRole> roles;

    public CurrentUser() {
        roles = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public CurrentUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CurrentUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public CurrentUser setRoles(Set<UserRole> roles) {
        this.roles = roles;
        return this;
    }

    public void addRole(UserRole role) {
        roles.add(role);
    }

    public boolean isAdmin() {
        return roles.stream().anyMatch(r -> r.getName().equals(UserRoleEnum.ADMIN));
    }
}
