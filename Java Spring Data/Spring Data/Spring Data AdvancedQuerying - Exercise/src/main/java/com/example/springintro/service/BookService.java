package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> findAllBookTitlesByMatchingAgeRestriction(AgeRestriction ageRestriction);

    List<String> findAllGoldTitleBooksWithCopiesLessThan(EditionType editionType, Integer copies);

    List<String> printBooksTitleAndPriceWithPriceNotBetweenRange(BigDecimal lowerPrice, BigDecimal upperPrice);

    List<String> printBooksTitlesNotReleasedInYear(Integer year);

    List<String> printBooksReleasedBeforeDate(LocalDate localDate);

    List<String> printAllBooksByTitleContainingString(String str);

    List<String> printAllBooksTitlesWrittenByAuthorsWithLastNameEndingWith(String str);

    int printCountOfBooksWithTitleLengthMoreThan(int length);

    String printBookByTitle(String title);

    int increaseBookCopiesByDate(int copies, LocalDate releaseDate);

    int deleteBooksWithCopiesLessThan(int copies);

    Long countBooksByAuthor(String first_name, String last_name);
}
