package com.mikadev.quotesapi.mappers;

import com.mikadev.quotesapi.DTOs.AuthorDTO.AuthorGetDTO;
import com.mikadev.quotesapi.DTOs.AuthorDTO.AuthorPostDTO;
import com.mikadev.quotesapi.DTOs.AuthorDTO.AuthorPutDTO;
import com.mikadev.quotesapi.entities.AuthorEntity;

public class AuthorMapper {

    public static AuthorGetDTO toGetDTO(AuthorEntity entity) {
        if (entity == null) return null;
        return new AuthorGetDTO(
                entity.getId(),
                entity.getFirstName(),
                entity.getSecondName(),
                entity.getSurname()
        );
    }

    public static AuthorEntity toAuthorEntity(AuthorPostDTO dto) {
        if (dto == null) return null;
        AuthorEntity entity = new AuthorEntity();
        entity.setFirstName(dto.firstName());
        entity.setSecondName(dto.secondName());
        entity.setSurname(dto.surname());
        return entity;
    }

    public static void updateAuthorEntity(AuthorEntity entity, AuthorPutDTO dto) {
        if (entity == null || dto == null) return;
        entity.setFirstName(dto.firstName());
        entity.setSecondName(dto.secondName());
        entity.setSurname(dto.surname());
    }
}
