package com.example.javaspringexercise.service.impl;

import com.example.javaspringexercise.model.*;
import com.example.javaspringexercise.repository.BookRepository;
import com.example.javaspringexercise.service.AuthorService;
import com.example.javaspringexercise.service.BookService;
import com.example.javaspringexercise.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;


    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() > 0) {
            return;
        }
        Files.readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookData = row.split("\\s+");
                    EditionType editionType = EditionType.values()[Integer.parseInt(bookData[0])];
                    LocalDate releaseDate = LocalDate.parse(bookData[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
                    int copies = Integer.parseInt(bookData[2]);
                    BigDecimal price = new BigDecimal(bookData[3]);
                    AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(bookData[4])];
                    String title = Arrays.stream(bookData)
                            .skip(5)
                            .collect(Collectors.joining(" "));

                    Author author = authorService.getRandomAuthor();
                    Set<Category> category = categoryService.getRandomCategories();

                    Book book = new Book(title, editionType, price, copies, releaseDate, ageRestriction,
                            author, category);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAllBooksAfterYear(int year) {
       return bookRepository.findAllByReleaseDateAfter(LocalDate.of(year - 1, 12, 31));
    }

    @Override
    public Set<Book> findAllBooksBeforeYear(int releaseYear) {
        return bookRepository.findAllByReleaseDateBefore(LocalDate.of(releaseYear, 1, 1));
    }

    @Override
    public List<String> findAllBooksByAuthorNameOrderByReleaseDateAndTitle(String firstName, String lastName) {

       return bookRepository.findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("title: %s%nrelease date: %s%ncopies: %d",
                        book.getTitle(), book.getReleaseDate().toString(), book.getCopies()))
                .collect(Collectors.toList());
    }


}
