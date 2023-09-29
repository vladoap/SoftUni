package com.example.books.service;

import com.example.books.model.dto.BookDto;

import java.util.List;

public interface BookService {

    public List<BookDto> getAllBooks();

    Long createBook(BookDto bookDto);

    BookDto findById(Long id);

    BookDto update(Long id, BookDto bookDto);

    boolean exists(Long id);

    boolean delete(Long id);
}
