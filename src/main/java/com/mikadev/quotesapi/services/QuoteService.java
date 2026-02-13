package com.mikadev.quotesapi.services;

import com.mikadev.quotesapi.DTOs.QuoteDTO.QuoteGetDTO;
import com.mikadev.quotesapi.DTOs.QuoteDTO.QuotePostDTO;
import com.mikadev.quotesapi.DTOs.QuoteDTO.QuotePutDTO;
import com.mikadev.quotesapi.entities.AuthorEntity;
import com.mikadev.quotesapi.entities.CategoryEntity;
import com.mikadev.quotesapi.entities.QuoteEntity;
import com.mikadev.quotesapi.exceptions.ResourceNotFoundException;
import com.mikadev.quotesapi.mappers.QuoteMapper;
import com.mikadev.quotesapi.repositories.AuthorRepository;
import com.mikadev.quotesapi.repositories.CategoryRepository;
import com.mikadev.quotesapi.repositories.QuoteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public QuoteService(QuoteRepository quoteRepository,
                        AuthorRepository authorRepository,
                        CategoryRepository categoryRepository) {
        this.quoteRepository = quoteRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<QuoteGetDTO> findAll() {
        return quoteRepository.findAll()
                .stream()
                .map(QuoteMapper::toGetDTO)
                .toList();
    }

    public QuoteGetDTO findById(Long id) {
        QuoteEntity quoteEntity = quoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quote not found"));
        return QuoteMapper.toGetDTO(quoteEntity);
    }

    public QuoteGetDTO create(QuotePostDTO dto) {
        AuthorEntity author = authorRepository.findById(dto.authorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        CategoryEntity category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        QuoteEntity quoteEntity = QuoteMapper.toQuoteEntity(dto, author, category);
        return QuoteMapper.toGetDTO(quoteRepository.save(quoteEntity));
    }

    public QuoteGetDTO update(Long id, QuotePutDTO dto) {
        QuoteEntity quoteToUpdate = quoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quote not found"));

        AuthorEntity author = authorRepository.findById(dto.authorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        CategoryEntity category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        QuoteMapper.updateQuoteEntity(quoteToUpdate, dto, author, category);
        return QuoteMapper.toGetDTO(quoteRepository.save(quoteToUpdate));
    }

    public void delete(Long id) {
        if (!quoteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Quote not found");
        }
        quoteRepository.deleteById(id);
    }
}
