package org.matusikl.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.matusikl.model.Role;
import org.matusikl.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RoleController {

    RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @Operation(summary = "Get role", description = "Get role with specified id", tags = "Role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found role with specified id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Role.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content)})
    @GetMapping(path = "/roles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> getRole(@PathVariable("id") Integer id){
        Role role = roleService.getRole(id);
        return ResponseEntity
                .ok()
                .body(role);
    }

    @Operation(summary = "Get roles", description = "Get list of roles", tags = "Role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found list of roles",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Role.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content)})
    @GetMapping(path = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Role>> getRoles(){
        List<Role> roleList = roleService.getRoles();
        return ResponseEntity
                .ok()
                .body(roleList);
    }

    @Operation(summary = "Save role", description = "Save specified role", tags = "Role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saved specified role",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Role.class))}),
            @ApiResponse(responseCode = "400", description = "Error: validation of attributes",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Error: duplicate - data already exist in database",
                    content = @Content)})
    @PostMapping(path = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> addRole(@Valid @RequestBody Role role){
        Role addedRole = roleService.addRole(role);
        return ResponseEntity
                .ok()
                .body(addedRole);
    }

    @Operation(summary = "Delete role", description = "Delete role with specified id", tags = "Role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted role with specified id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Role.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content)})
    @DeleteMapping(path = "/roles/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> deleteRole(@PathVariable ("id") Integer id){
        roleService.deleteRole(id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(String.format("Role with id: %d deleted", id));
    }

    @Operation(summary = "Update role", description = "Update role with specified id", tags = "Role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated role with specified id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Role.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Error: duplicate - data already exist in database",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Error: validation of attributes",
                    content = @Content)})
    @PutMapping(path = "/roles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> updateRole(@Valid @RequestBody Role role,
                                           @PathVariable ("id") Integer id){
        Role updatedRole = roleService.updateRole(role, id);
        return ResponseEntity
                .ok()
                .body(updatedRole);
    }
}
