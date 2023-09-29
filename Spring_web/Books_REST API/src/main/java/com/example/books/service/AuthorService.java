package com.example.books.service;

import com.example.books.model.entity.AuthorEntity;

import java.util.Optional;

public interface AuthorService {

    Optional<AuthorEntity> findAuthorByName(String name);

    AuthorEntity save(AuthorEntity author);
}
