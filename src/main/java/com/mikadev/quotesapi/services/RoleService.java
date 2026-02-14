package com.mikadev.quotesapi.services;

import com.mikadev.quotesapi.DTOs.RoleDTO;
import com.mikadev.quotesapi.entities.RoleEntity;
import com.mikadev.quotesapi.exceptions.ResourceAlreadyExistsException;
import com.mikadev.quotesapi.exceptions.ResourceNotFoundException;
import com.mikadev.quotesapi.mappers.RoleMapper;
import com.mikadev.quotesapi.repositories.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Page<RoleDTO> findAll(Pageable pageable) {
        int maxSize = 20;
        int page = Math.max(pageable.getPageNumber(), 0);
        int size = Math.max(pageable.getPageSize(), maxSize);

        Pageable validatedPageable = PageRequest.of(page, size);

        return roleRepository.findAll(validatedPageable).
                map(RoleMapper::toDTO);
    }

    public RoleDTO findById(Long id) {
        return roleRepository.findById(id).
                map(RoleMapper::toDTO).
                orElseThrow(
                        () -> new ResourceNotFoundException("Role not found")
                );
    }

    public RoleDTO create(RoleDTO roleDTO) {
        RoleEntity roleEntity = RoleMapper.toEntity(roleDTO);
        if (roleRepository.existsByRoleName(roleDTO.roleName())) {
            throw new ResourceAlreadyExistsException("Role already exists");
        }
        RoleEntity roleEntitySaved = roleRepository.save(roleEntity);
        return RoleMapper.toDTO(roleEntitySaved);
    }

    public RoleDTO update(long id, RoleDTO roleDTO) {
        return roleRepository.findById(id)
                .map(entity -> {
                    RoleMapper.updateEntity(entity, roleDTO);
                    return RoleMapper.toDTO(roleRepository.save(entity));
                })
                .orElseThrow(
                        () -> new ResourceNotFoundException("Role not found")
                );
    }

    public void delete(long id) {
        if (!roleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Role not found");
        }
        roleRepository.deleteById(id);
    }
}
