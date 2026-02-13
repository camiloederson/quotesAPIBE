package com.mikadev.quotesapi.services;

import com.mikadev.quotesapi.DTOs.UserDTO.UserGetDTO;
import com.mikadev.quotesapi.DTOs.UserDTO.UserPostDTO;
import com.mikadev.quotesapi.DTOs.UserDTO.UserPutDTO;
import com.mikadev.quotesapi.entities.RoleEntity;
import com.mikadev.quotesapi.entities.UserEntity;
import com.mikadev.quotesapi.exceptions.ResourceAlreadyExistsException;
import com.mikadev.quotesapi.exceptions.ResourceNotFoundException;
import com.mikadev.quotesapi.mappers.UserMapper;
import com.mikadev.quotesapi.repositories.RoleRepository;
import com.mikadev.quotesapi.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<UserGetDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toGetDTO)
                .toList();
    }

    public UserGetDTO findUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toGetDTO)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public UserGetDTO createUser(UserPostDTO userPostDTO) {

        RoleEntity roleEntity = roleRepository.findById(userPostDTO.roleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        UserEntity userEntity = UserMapper.toEntity(userPostDTO, roleEntity);

        if (userRepository.existsByEmail(userPostDTO.email())
                || userRepository.existsByUsername(userPostDTO.username())) {
            throw new ResourceAlreadyExistsException("User already exists");
        }
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return UserMapper.toGetDTO(savedUserEntity);
    }

    public UserGetDTO updateUser(Long id, UserPutDTO userPutDTO) {
        RoleEntity roleEntity = roleRepository.findById(userPutDTO.roleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        UserEntity savedUserEntity = userRepository.save(UserMapper.updateEntity(userEntity, userPutDTO, roleEntity));
        return UserMapper.toGetDTO(savedUserEntity);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }
}
