package com.mikadev.quotesapi.controllers;

import com.mikadev.quotesapi.DTOs.RoleDTO;
import com.mikadev.quotesapi.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/quotes/api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<Page<RoleDTO>> getRoles(
            @RequestParam int page, @RequestParam int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<RoleDTO> roleEntityPage = roleService.findAll(pageable);
        return ResponseEntity.ok(roleEntityPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable long id) {
        RoleDTO dto = roleService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@Valid @RequestBody RoleDTO roleDTO) {
        RoleDTO createdRole = roleService.create(roleDTO);
        URI location = URI.create("quotes/v1/api/roles" + createdRole.id());
        return ResponseEntity.created(location).body(createdRole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable long id,
                                              @Valid @RequestBody RoleDTO roleDTO) {
        RoleDTO updatedRole = roleService.update(id, roleDTO);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
