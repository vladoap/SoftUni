package com.example.mobilelele_web.repository;

import com.example.mobilelele_web.model.entity.UserRole;
import com.example.mobilelele_web.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findByRole(Role role);
}
