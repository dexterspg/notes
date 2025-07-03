package com.example.hateoas.controller;

import com.example.hateoas.entity.Role;
import com.example.hateoas.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * REST Controller for Role entity demonstrating HATEOAS with @ManyToMany relationship
 * 
 * This controller shows how to handle the non-owning side of a @ManyToMany relationship
 */
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Get all roles with HATEOAS links
     * 
     * Demonstrates:
     * - CollectionModel with links
     * - Links to related users (@ManyToMany relationship)
     */
    @GetMapping
    public CollectionModel<EntityModel<Role>> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        
        List<EntityModel<Role>> roleModels = roles.stream()
            .map(role -> EntityModel.of(role)
                // Self link
                .add(linkTo(methodOn(RoleController.class).getRoleById(role.getId())).withSelfRel())
                // Link to users with this role (demonstrating @ManyToMany relationship)
                .add(linkTo(methodOn(RoleController.class).getUsersByRoleId(role.getId())).withRel("users"))
                // Link to update role
                .add(linkTo(methodOn(RoleController.class).updateRole(role.getId(), null)).withRel("update"))
                // Link to delete role
                .add(linkTo(methodOn(RoleController.class).deleteRole(role.getId())).withRel("delete"))
            )
            .collect(Collectors.toList());
        
        return CollectionModel.of(roleModels)
            .add(linkTo(methodOn(RoleController.class).getAllRoles()).withSelfRel())
            .add(linkTo(methodOn(RoleController.class).createRole(null)).withRel("create"));
    }

    /**
     * Get role by ID with HATEOAS links
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Role>> getRoleById(@PathVariable Long id) {
        return roleRepository.findById(id)
            .map(role -> EntityModel.of(role)
                .add(linkTo(methodOn(RoleController.class).getRoleById(id)).withSelfRel())
                .add(linkTo(methodOn(RoleController.class).getAllRoles()).withRel("roles"))
                .add(linkTo(methodOn(RoleController.class).getUsersByRoleId(id)).withRel("users"))
                .add(linkTo(methodOn(RoleController.class).updateRole(id, null)).withRel("update"))
                .add(linkTo(methodOn(RoleController.class).deleteRole(id)).withRel("delete"))
            )
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get users by role ID (demonstrating @ManyToMany relationship)
     * 
     * This method shows how to navigate the @ManyToMany relationship
     * from the non-owning side (Role) to the owning side (User)
     */
    @GetMapping("/{roleId}/users")
    public ResponseEntity<CollectionModel<EntityModel<Object>>> getUsersByRoleId(@PathVariable Long roleId) {
        return roleRepository.findById(roleId)
            .map(role -> {
                List<EntityModel<Object>> userModels = role.getUsers().stream()
                    .map(user -> EntityModel.of((Object) user)
                        .add(linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel())
                        .add(linkTo(methodOn(RoleController.class).getRoleById(roleId)).withRel("role"))
                    )
                    .collect(Collectors.toList());
                
                return CollectionModel.of(userModels)
                    .add(linkTo(methodOn(RoleController.class).getUsersByRoleId(roleId)).withSelfRel())
                    .add(linkTo(methodOn(RoleController.class).getRoleById(roleId)).withRel("role"))
                    .add(linkTo(methodOn(UserController.class).getAllUsers()).withRel("all-users"));
            })
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create new role
     */
    @PostMapping
    public ResponseEntity<EntityModel<Role>> createRole(@RequestBody Role role) {
        Role savedRole = roleRepository.save(role);
        
        EntityModel<Role> roleModel = EntityModel.of(savedRole)
            .add(linkTo(methodOn(RoleController.class).getRoleById(savedRole.getId())).withSelfRel())
            .add(linkTo(methodOn(RoleController.class).getAllRoles()).withRel("roles"))
            .add(linkTo(methodOn(RoleController.class).getUsersByRoleId(savedRole.getId())).withRel("users"));
        
        return ResponseEntity
            .created(linkTo(methodOn(RoleController.class).getRoleById(savedRole.getId())).toUri())
            .body(roleModel);
    }

    /**
     * Update existing role
     */
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Role>> updateRole(@PathVariable Long id, @RequestBody Role roleDetails) {
        return roleRepository.findById(id)
            .map(role -> {
                role.setName(roleDetails.getName());
                role.setDescription(roleDetails.getDescription());
                Role updatedRole = roleRepository.save(role);
                
                return EntityModel.of(updatedRole)
                    .add(linkTo(methodOn(RoleController.class).getRoleById(id)).withSelfRel())
                    .add(linkTo(methodOn(RoleController.class).getAllRoles()).withRel("roles"))
                    .add(linkTo(methodOn(RoleController.class).getUsersByRoleId(id)).withRel("users"));
            })
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete role
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        return roleRepository.findById(id)
            .map(role -> {
                roleRepository.delete(role);
                return ResponseEntity.noContent().build();
            })
            .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Find role by name
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<EntityModel<Role>> getRoleByName(@PathVariable String name) {
        return roleRepository.findByName(name)
            .map(role -> EntityModel.of(role)
                .add(linkTo(methodOn(RoleController.class).getRoleById(role.getId())).withSelfRel())
                .add(linkTo(methodOn(RoleController.class).getAllRoles()).withRel("roles"))
                .add(linkTo(methodOn(RoleController.class).getUsersByRoleId(role.getId())).withRel("users"))
            )
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}