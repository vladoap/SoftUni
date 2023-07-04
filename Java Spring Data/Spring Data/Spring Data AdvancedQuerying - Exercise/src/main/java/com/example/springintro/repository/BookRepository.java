package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, Integer copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lower, BigDecimal upper);

    List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate releaseDate1, LocalDate releaseDate2);

    List<Book> findAllByTitleContaining(String title);

    List<Book> findAllByAuthorLastNameStartingWith(String str);

    @Query("SELECT count(*) FROM Book b WHERE length(b.title) > :param")
    int countAllByTitleLengthGraterThan(@Param(value = "param") int titleLength);

    @Query("SELECT b.title, b.editionType, b.ageRestriction, b.price FROM Book b WHERE b.title = :title")
    Object[] findBookByTitle(String title);


    @Query("UPDATE Book b SET b.copies = b.copies + :paramCopies WHERE b.releaseDate > :date")
    @Modifying
    int updateAllByReleaseDateAfterIncreaseCopies(Integer paramCopies, LocalDate date);


    int deleteAllByCopiesLessThan(Integer copies);

    @Procedure(name = "getAmountBooksByAuthor")
    Long countBooksByAuthor(@Param(value = "first_name") String firstName, @Param(value = "last_name") String lastName);
}
