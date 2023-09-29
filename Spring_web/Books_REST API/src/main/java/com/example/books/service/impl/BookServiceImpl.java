package com.example.books.service.impl;

import com.example.books.model.dto.AuthorDto;
import com.example.books.model.dto.BookDto;
import com.example.books.model.entity.AuthorEntity;
import com.example.books.model.entity.BookEntity;
import com.example.books.repository.BookRepository;
import com.example.books.service.AuthorService;
import com.example.books.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.authorService = authorService;
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<BookDto> books =  bookRepository.findAll()
                .stream()
                .map(book -> {
                    AuthorDto authorDto = new AuthorDto()
                            .setName(book.getAuthor().getName());

                    return new BookDto()
                            .setId(book.getId())
                            .setTitle(book.getTitle())
                            .setIsbn(book.getIsbn())
                            .setAuthor(authorDto);
                } )
                .collect(Collectors.toList());

        return books;
    }

    @Override
    public Long createBook(BookDto bookDto) {
        String authorName = bookDto.getAuthor().getName();
        BookEntity book = modelMapper.map(bookDto, BookEntity.class);

        Optional<AuthorEntity> authorOpt = authorService.findAuthorByName(authorName);
        if (authorOpt.isEmpty()) {
             authorService.save(new AuthorEntity().setName(authorName));

        }
            book.setAuthor(authorService.findAuthorByName(authorName).get());



       return bookRepository.save(book).getId();
    }

    @Override
    public BookDto findById(Long id) {
        Optional<BookEntity> bookOpt = bookRepository.findById(id);
        return bookOpt.map(book -> modelMapper.map(book, BookDto.class)).orElse(null);
    }

    @Override
    public BookDto update(Long id, BookDto bookDto) {
        Optional<BookEntity> bookEntity = bookRepository.findById(id);

        if (bookEntity.isEmpty() || bookDto == null) {
            return null;
        }

        String authorName = bookDto.getAuthor().getName();

        Optional<AuthorEntity> author = authorService.findAuthorByName(authorName);
        if (author.isEmpty()) {
            AuthorEntity authorEntity = new AuthorEntity();
            authorEntity.setName(authorName);

            authorService.save(authorEntity);
            bookEntity.get().setAuthor(authorEntity);
        } else {
            bookEntity.get().setAuthor(author.get());
        }

        bookEntity.get()
                .setIsbn(bookDto.getIsbn())
                .setTitle(bookDto.getTitle());

        return modelMapper.map(bookRepository.save(bookEntity.get()), BookDto.class);


    }


    @Override
    public boolean exists(Long id) {
        return bookRepository.existsById(id);
    }

    @Override
    public boolean delete(Long id) {
        Optional<BookEntity> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(id);
            return true;
        }


        return false;
    }
}
