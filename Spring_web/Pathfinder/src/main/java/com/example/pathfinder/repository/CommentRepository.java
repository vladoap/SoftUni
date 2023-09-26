package com.example.pathfinder.repository;


import com.example.pathfinder.model.entity.Comment;
import com.example.pathfinder.model.entity.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c.route FROM Comment c GROUP BY c.route.id ORDER BY count(c) DESC")
    Page<Route> findTheMostCommentedRoute(Pageable pageable);
}
