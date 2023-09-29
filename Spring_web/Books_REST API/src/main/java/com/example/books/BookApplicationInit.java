package com.example.books;

import com.example.books.model.entity.AuthorEntity;
import com.example.books.model.entity.BookEntity;
import com.example.books.repository.AuthorRepository;
import com.example.books.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class BookApplicationInit implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookApplicationInit(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (authorRepository.count() == 0 && bookRepository.count() == 0) {
            initJovkov();
            initVazov();
            initElinPelin();
        }
    }

    private void initElinPelin() {
   iniAuthor("Елин Пелин", "Пижо и Пендо", "Ян Бибиян на луната", "Под манастирската лоза");
    }

    private void initVazov() {
        iniAuthor("Иван Вазов", "Пряпорец и Гусла", "Под Игото", "Тъгите на България");
    }

    private void initJovkov() {
        iniAuthor("Йордан Йовков", "Старопланински легенди", "Чифликът край границата");
    }

    private void iniAuthor(String authorName, String... books) {
        AuthorEntity author = new AuthorEntity();
        author.setName(authorName);
        author = authorRepository.save(author);

        List<BookEntity> allBooks = new ArrayList<>();

        for (String book : books) {
            BookEntity aBook = new BookEntity();
            aBook.setAuthor(author);
            aBook.setTitle(book);
            aBook.setIsbn(UUID.randomUUID().toString());
            bookRepository.save(aBook);

        }


        author.setBooks(allBooks);
        authorRepository.save(author);
    }


}
