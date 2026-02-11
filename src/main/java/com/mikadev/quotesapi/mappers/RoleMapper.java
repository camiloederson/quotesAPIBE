package com.mikadev.quotesapi.mappers;

import com.mikadev.quotesapi.DTOs.RoleDTO;
import com.mikadev.quotesapi.entities.RoleEntity;

public class RoleMapper {
    public static RoleDTO toDTO(RoleEntity entity) {
        return new RoleDTO(entity.getId(), entity.getRoleName(), entity.getDescription());
    }

    public static RoleEntity toEntity(RoleDTO dto) {
        RoleEntity entity = new RoleEntity();
        entity.setRoleName(dto.roleName());
        entity.setDescription(dto.description());
        return entity;
    }

    public static RoleEntity updateEntity(RoleEntity entity, RoleDTO dto) {
        entity.setRoleName(dto.roleName());
        entity.setDescription(dto.description());
        return entity;
    }
}
