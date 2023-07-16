package com.example.productshopdatabase.repository;

import com.example.productshopdatabase.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE (SELECT count(*) FROM Product p WHERE u.id = p.seller.id AND p.buyer IS NOT NULL) > 0" +
            " ORDER BY u.lastName, u.firstName")
   List<User> findAllUsersWithSoldProductsOrderedByLastNameAndFirstName();


    @Query("SELECT u FROM User u JOIN u.soldProducts p WHERE u.id = p.seller.id AND p.buyer IS NOT NULL GROUP BY u.id HAVING COUNT(p) > 0 " +
            "ORDER BY COUNT(p) DESC, u.lastName ASC")
    List<User> findAllUsersWithAtLeastOneProductSoldOrderByProductsCount();





}
