package com.mikadev.quotesapi.mappers;

import com.mikadev.quotesapi.DTOs.QuoteDTO.QuoteGetDTO;
import com.mikadev.quotesapi.DTOs.QuoteDTO.QuotePostDTO;
import com.mikadev.quotesapi.DTOs.QuoteDTO.QuotePutDTO;
import com.mikadev.quotesapi.entities.AuthorEntity;
import com.mikadev.quotesapi.entities.CategoryEntity;
import com.mikadev.quotesapi.entities.QuoteEntity;

public class QuoteMapper {

    public static QuoteGetDTO toGetDTO(QuoteEntity entity) {
        if (entity == null) return null;
        return new QuoteGetDTO(
                entity.getId(),
                entity.getQuote(),
                entity.getAuthor().getId(),
                entity.getAuthor().getFirstName(),
                entity.getCategory().getId(),
                entity.getCategory().getName()
        );
    }

    public static QuoteEntity toQuoteEntity(QuotePostDTO dto, AuthorEntity author, CategoryEntity category) {
        if (dto == null) return null;
        QuoteEntity entity = new QuoteEntity();
        entity.setQuote(dto.quote());
        entity.setAuthor(author);
        entity.setCategory(category);
        return entity;
    }

    public static void updateQuoteEntity(QuoteEntity entity, QuotePutDTO dto, AuthorEntity author, CategoryEntity category) {
        if (entity == null || dto == null) return;
        entity.setQuote(dto.quote());
        entity.setAuthor(author);
        entity.setCategory(category);
    }
}
