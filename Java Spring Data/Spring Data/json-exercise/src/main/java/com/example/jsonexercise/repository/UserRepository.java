package com.example.jsonexercise.repository;

import com.example.jsonexercise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query("SELECT u FROM User u " +
            "WHERE (SELECT count(*) FROM Product p WHERE p.seller.id = u.id AND p.buyer IS NOT NULL) > 0 ORDER BY u.firstName, u.lastName")
    List<User> findAllUsersWithAtLeastOneSoldProductOrderByLastNameAndFirstName();


    @Query("SELECT u FROM User u JOIN u.soldProducts sp WHERE sp.buyer IS NOT NULL ORDER BY SIZE(u.soldProducts) DESC, u.lastName")
    List<User> findAllByUsersWithAtLeastOneSoldProductOrderedByCountOfProducts();


}
