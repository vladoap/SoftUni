package com.example.books.service.impl;

import com.example.books.model.entity.AuthorEntity;
import com.example.books.repository.AuthorRepository;
import com.example.books.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<AuthorEntity> findAuthorByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    public AuthorEntity save(AuthorEntity authorDto) {
         return authorRepository.save(modelMapper.map(authorDto, AuthorEntity.class));
    }
}
