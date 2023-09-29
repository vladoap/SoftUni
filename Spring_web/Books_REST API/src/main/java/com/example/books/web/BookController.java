package com.example.books.web;

import com.example.books.model.dto.BookDto;
import com.example.books.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        BookDto bookDto = bookService.findById(id);

        if (bookDto != null) {
            return ResponseEntity.ok(bookDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<BookDto> create(@RequestBody BookDto bookDto, UriComponentsBuilder uriComponentsBuilder) {
        Long bookId = bookService.createBook(bookDto);

        URI location = uriComponentsBuilder.path("/api/books/{id}")
                .build(bookId);

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BookDto> update(@PathVariable Long id, @RequestBody BookDto bookDto, UriComponentsBuilder uriComponentsBuilder) {

        if (!bookService.exists(id)) {
            return ResponseEntity.notFound().build();
        }

        BookDto updatedBook = bookService.update(id, bookDto);

        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BookDto> delete(@PathVariable Long id) {
        boolean isDeleted = bookService.delete(id);

        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }

            return ResponseEntity.notFound().build();

    }

}
