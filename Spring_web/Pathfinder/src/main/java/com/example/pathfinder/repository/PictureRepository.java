package com.example.pathfinder.repository;

import com.example.pathfinder.model.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

    @Query("SELECT p.url FROM Picture p WHERE p.route.id = :routeId AND p.id = (SELECT MIN(p2.id)FROM Picture p2 WHERE p2.route.id = :routeId)")
    String findFirstPictureByRouteId(Long routeId);

    List<Picture> findAllByRouteId(Long routeId);

    List<Picture> findAllByAuthorId(Long authorId);
}
