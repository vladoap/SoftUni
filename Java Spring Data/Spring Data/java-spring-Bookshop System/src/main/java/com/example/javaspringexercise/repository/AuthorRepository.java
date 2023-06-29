package com.example.javaspringexercise.repository;

import com.example.javaspringexercise.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {


    @Query("FROM Author a ORDER BY SIZE(a.books) DESC")
    Set<Author> findAllByBooksSizeDESC();


}
