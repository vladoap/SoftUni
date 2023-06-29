package com.example.javaspringexercise.service;

import com.example.javaspringexercise.model.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> findAllAuthorsOrderByCountOfBooksSizeDESC();
}
