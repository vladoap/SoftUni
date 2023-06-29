package com.example.javaspringexercise.service;

import com.example.javaspringexercise.model.Book;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface BookService {
    void seedBooks() throws IOException;


    List<Book> findAllBooksAfterYear(int year);

    Set<Book> findAllBooksBeforeYear(int releaseYear);

    List<String> findAllBooksByAuthorNameOrderByReleaseDateAndTitle(String firstName, String lastName);
}
