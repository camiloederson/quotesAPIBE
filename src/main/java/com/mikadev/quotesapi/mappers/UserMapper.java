package com.mikadev.quotesapi.mappers;

import com.mikadev.quotesapi.DTOs.RoleDTO;
import com.mikadev.quotesapi.DTOs.UserDTO.UserGetDTO;
import com.mikadev.quotesapi.DTOs.UserDTO.UserPostDTO;
import com.mikadev.quotesapi.DTOs.UserDTO.UserPutDTO;
import com.mikadev.quotesapi.entities.RoleEntity;
import com.mikadev.quotesapi.entities.UserEntity;

public class UserMapper {

    public static UserGetDTO toGetDTO(UserEntity entity) {
        return new UserGetDTO(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getUsername(),
                entity.getEmail(),
                new RoleDTO(
                        entity.getRole().getId(),
                        entity.getRole().getRoleName(),
                        entity.getRole().getDescription()
                )
        );
    }

    public static UserEntity toEntity(UserPostDTO dto, RoleEntity roleEntity) {
        UserEntity entity = new UserEntity();
        entity.setFirstName(dto.firstName());
        entity.setLastName(dto.lastName());
        entity.setUsername(dto.username());
        entity.setEmail(dto.email());
        entity.setPassword(dto.password());
        entity.setRole(roleEntity);
        return entity;
    }

    public static UserEntity updateEntity(UserEntity entity, UserPutDTO dto, RoleEntity roleEntity) {
        entity.setFirstName(dto.firstName());
        entity.setLastName(dto.lastName());
        entity.setUsername(dto.username());
        entity.setEmail(dto.email());
        entity.setPassword(dto.password());
        entity.setRole(roleEntity);
        return entity;
    }
}
