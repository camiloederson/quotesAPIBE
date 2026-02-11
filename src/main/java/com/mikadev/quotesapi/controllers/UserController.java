package com.mikadev.quotesapi.controllers;

import com.mikadev.quotesapi.DTOs.UserDTO.UserGetDTO;
import com.mikadev.quotesapi.DTOs.UserDTO.UserPostDTO;
import com.mikadev.quotesapi.DTOs.UserDTO.UserPutDTO;
import com.mikadev.quotesapi.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/quotes/v1/api/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserGetDTO>> findAllUsers() {
        List<UserGetDTO> usersEntityList = userService.findAll();
        return ResponseEntity.ok(usersEntityList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGetDTO> findUserById(@PathVariable("id") Long id) {
        UserGetDTO user = userService.findUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserGetDTO> createUser(@Valid @RequestBody UserPostDTO user) {
        UserGetDTO createdUser = userService.createUser(user);
        URI location = URI.create("/quotes/v1/api/users/" + createdUser.id());
        return ResponseEntity.created(location).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserGetDTO> updateUser(@Valid @PathVariable("id") Long id, @Valid @RequestBody UserPutDTO user) {
        UserGetDTO updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
