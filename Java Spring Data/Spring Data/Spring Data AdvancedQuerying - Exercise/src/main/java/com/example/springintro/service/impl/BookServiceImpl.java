package com.example.springintro.service.impl;

import com.example.springintro.model.entity.*;
import com.example.springintro.repository.BookRepository;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        Files
                .readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBookFromInfo(bookInfo);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAllBooksAfterYear(int year) {
        return bookRepository
                .findAllByReleaseDateAfter(LocalDate.of(year, 12, 31));
    }

    @Override
    public List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year) {
        return bookRepository
                .findAllByReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream()
                .map(book -> String.format("%s %s", book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName) {
       return bookRepository
                .findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("%s %s %d",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBookTitlesByMatchingAgeRestriction(AgeRestriction ageRestriction) {
        return bookRepository.findAllByAgeRestriction(ageRestriction)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllGoldTitleBooksWithCopiesLessThan(EditionType editionType, Integer copies) {
       return bookRepository.findAllByEditionTypeAndCopiesLessThan(editionType, copies)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> printBooksTitleAndPriceWithPriceNotBetweenRange(BigDecimal lowerPrice, BigDecimal upperPrice) {
        return bookRepository.findAllByPriceLessThanOrPriceGreaterThan(lowerPrice, upperPrice)
                .stream()
                .map(book -> String.format("%s - $%s",
                        book.getTitle(),
                        book.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> printBooksTitlesNotReleasedInYear(Integer year) {
        LocalDate localDateBefore = LocalDate.of(year, 1, 1);
        LocalDate localDateAfter = LocalDate.of(year, 12, 31);
        return bookRepository
                .findAllByReleaseDateBeforeOrReleaseDateAfter(localDateBefore, localDateAfter)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> printBooksReleasedBeforeDate(LocalDate localDate) {
        return bookRepository.findAllByReleaseDateBefore(localDate)
                .stream()
                .map(book -> String.format("%s %s %s",
                        book.getTitle(), book.getEditionType().toString(), book.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> printAllBooksByTitleContainingString(String str) {
        return bookRepository.findAllByTitleContaining(str)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> printAllBooksTitlesWrittenByAuthorsWithLastNameEndingWith(String str) {
        return bookRepository.findAllByAuthorLastNameStartingWith(str)
                .stream()
                .map(book -> String.format("%s (%s)",
                        book.getTitle(), book.getAuthor().getFirstName() + book.getAuthor().getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public int printCountOfBooksWithTitleLengthMoreThan(int length) {
        return bookRepository.countAllByTitleLengthGraterThan(length);
    }

    @Override
    public String printBookByTitle(String title) {

        Object[] bookByTitle = bookRepository.findBookByTitle(title);

        return String.format("%s %s %s %s",
                (String)bookByTitle[0],
                (String)bookByTitle[1].toString(),
                (String)bookByTitle[2].toString(),
                (String)bookByTitle[3]);
    }

    @Override
    @Transactional
    public int increaseBookCopiesByDate(int copies, LocalDate releaseDate) {
        return bookRepository.updateAllByReleaseDateAfterIncreaseCopies(copies, releaseDate);
    }

    @Override
    @Transactional
    public int deleteBooksWithCopiesLessThan(int copies) {
        return bookRepository.deleteAllByCopiesLessThan(copies);
    }

    @Override
    public Long countBooksByAuthor(String first_name, String last_name) {

        return bookRepository.countBooksByAuthor(first_name, last_name);

    }

    private Book createBookFromInfo(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate
                .parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction
                .values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService
                .getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);

    }
}
