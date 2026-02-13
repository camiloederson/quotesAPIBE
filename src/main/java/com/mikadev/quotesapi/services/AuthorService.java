package com.mikadev.quotesapi.services;

import com.mikadev.quotesapi.DTOs.AuthorDTO.AuthorGetDTO;
import com.mikadev.quotesapi.entities.AuthorEntity;
import com.mikadev.quotesapi.exceptions.ResourceAlreadyExistsException;
import com.mikadev.quotesapi.exceptions.ResourceNotFoundException;
import com.mikadev.quotesapi.repositories.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import com.mikadev.quotesapi.mappers.AuthorMapper;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorGetDTO> findAll() {
        return authorRepository.findAll()
                .stream()
                .map(AuthorMapper::toGetDTO)
                .toList();
    }

    public AuthorGetDTO findById(Long id) {
        AuthorEntity authorEntity = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        return AuthorMapper.toGetDTO(authorEntity);
    }

    public AuthorGetDTO create(AuthorGetDTO dto) {
        if (authorRepository.existsByFirstNameAndSecondNameAndSurname(dto.firstName(), dto.secondName(), dto.surname())) {
            throw new ResourceAlreadyExistsException("Author already exists");
        }
        AuthorEntity authorEntity = new AuthorEntity();
        return AuthorMapper.toGetDTO(authorRepository.save(authorEntity));
    }

    public AuthorGetDTO update(Long id, AuthorGetDTO dto) {
        AuthorEntity authorToUpdate = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        return AuthorMapper.toGetDTO(authorRepository.save(authorToUpdate));
    }

    public void delete(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Author not found");
        }
        authorRepository.deleteById(id);
    }
}
