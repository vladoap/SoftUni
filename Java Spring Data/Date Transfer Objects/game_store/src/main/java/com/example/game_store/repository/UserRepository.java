package com.example.game_store.repository;

import com.example.game_store.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

     Optional<User> findUserByEmailAndPassword(String email, String password);

     User findByFullName(String fullName);
}
