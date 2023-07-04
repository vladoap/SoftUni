package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader bufferedReader;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService, BufferedReader bufferedReader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

//        //printAllBooksAfterYear(2000);
////        printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
//     //   printAllAuthorsAndNumberOfTheirBooks();
//        pritnALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");

        System.out.println("Enter task number");
        int taskNum = Integer.parseInt(bufferedReader.readLine());

        switch (taskNum) {
            case 1 -> exOne();
            case 2 -> exTwo();
            case 3 -> exThree();
            case 4 -> exFour();
            case 5 -> exFive();
            case 6 -> exSix();
            case 7 -> exSeven();
            case 8 -> exEight();
            case 9 -> exNine();
            case 10 -> exTen();
            case 11 -> exEleven();
            case 12 -> exTwelve();
            case 13 -> exThirteen();
            case 14 -> exFourteen();



        }

    }

    private void exFourteen() throws IOException {
        System.out.println("Enter author's name:");
        String[] names = bufferedReader.readLine().split("\\s+");
        String firstName = names[0];
        String lastName = names[1];

        System.out.println(bookService.countBooksByAuthor(firstName, lastName));


    }

    private void exThirteen() throws IOException {
        System.out.println("Enter copies:");
        int copies = Integer.parseInt(bufferedReader.readLine());

        System.out.println(bookService.deleteBooksWithCopiesLessThan(copies));
    }

    private void exTwelve() throws IOException {
        System.out.println("Enter date:");
        LocalDate releaseDate = LocalDate.parse(bufferedReader.readLine(), DateTimeFormatter.ofPattern("dd MMM yyyy"));
        System.out.println("Enter copies:");
        int copies = Integer.parseInt(bufferedReader.readLine());
        System.out.println(bookService.increaseBookCopiesByDate(copies, releaseDate));
    }

    private void exEleven() throws IOException {
        System.out.println("Enter book title");
        String title = bufferedReader.readLine();

        System.out.println(bookService.printBookByTitle(title));


    }

    private void exTen() {
        authorService.printTotalBookCopiesByAuthor()
                .forEach(System.out::println);

    }

    private void exNine() throws IOException {
        System.out.println("Enter title length:");
        int length = Integer.parseInt(bufferedReader.readLine());
        System.out.println(bookService.printCountOfBooksWithTitleLengthMoreThan(length));
    }

    private void exEight() throws IOException {
        System.out.println("Enter starting string:");
        String str = bufferedReader.readLine();

        bookService.printAllBooksTitlesWrittenByAuthorsWithLastNameEndingWith(str)
                .forEach(System.out::println);

    }

    private void exSeven() throws IOException {
        System.out.println("Enter containing string:");
        String str = bufferedReader.readLine();

        bookService.printAllBooksByTitleContainingString(str)
                .forEach(System.out::println);
    }

    private void exSix() throws IOException {
        System.out.println("Enter ending string");
        String endStr = bufferedReader.readLine();

        authorService.printAllAuthorsWhoseNamesEndWith(endStr)
                .forEach(System.out::println);

    }

    private void exFive() throws IOException {
        System.out.println("Enter date:");
        LocalDate localDate = LocalDate.parse(bufferedReader.readLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        bookService.printBooksReleasedBeforeDate(localDate)
                .forEach(System.out::println);
    }

    private void exFour() throws IOException {
        Integer year = Integer.valueOf(bufferedReader.readLine());
        bookService.printBooksTitlesNotReleasedInYear(year)
                .forEach(System.out::println);
    }

    private void exThree() {
        BigDecimal lowerPrice = new BigDecimal(5);
        BigDecimal upperPrice = new BigDecimal(40);
        bookService.printBooksTitleAndPriceWithPriceNotBetweenRange(lowerPrice, upperPrice)
                .forEach(System.out::println);
    }

    private void exTwo() {
        EditionType editionType = EditionType.GOLD;
        Integer copies = 5000;

         bookService.findAllGoldTitleBooksWithCopiesLessThan(editionType, copies)
                 .forEach(System.out::println);
    }

    private void exOne() throws IOException {
        AgeRestriction ageRestriction = AgeRestriction.valueOf(bufferedReader.readLine().toUpperCase());


        bookService.findAllBookTitlesByMatchingAgeRestriction(ageRestriction)
                .forEach(System.out::println);

    }

    private void pritnALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
