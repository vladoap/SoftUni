package com.example.javaspringexercise;

import com.example.javaspringexercise.model.Book;
import com.example.javaspringexercise.service.AuthorService;
import com.example.javaspringexercise.service.BookService;
import com.example.javaspringexercise.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final AuthorService authorService;
    private final BookService bookService;
    private final CategoryService categoryService;

    public ConsoleRunner(AuthorService authorService, BookService bookService, CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedDatabase();

        printAllBooksAfterYear(2000);

        printAllAuthorsNamesWithBookWithReleaseDateBeforeYear(1990);

        printAllAuthorsNamesOrderByCountOfBooksDESC();


        printAllBooksByAuthorName("George", "Powell");
    }

    private void printAllBooksByAuthorName(String firstName, String lastName) {
        bookService.findAllBooksByAuthorNameOrderByReleaseDateAndTitle(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesOrderByCountOfBooksDESC() {
        authorService.findAllAuthorsOrderByCountOfBooksSizeDESC()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBookWithReleaseDateBeforeYear(int releaseYear) {

        bookService.findAllBooksBeforeYear(releaseYear)
                .stream()
                .map(book -> String.format("%s %s : %d",
                        book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName(),
                        book.getAuthor().getBooks().size()))
                .distinct()
                .forEach(System.out::println);


    }

    private void printAllBooksAfterYear(int year) {
      bookService
              .findAllBooksAfterYear(year)
              .stream()
              .map(Book::getTitle)
              .forEach(System.out::println);
    }

    private void seedDatabase() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
