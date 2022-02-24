package org.matusikl.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.matusikl.dto.roledto.RoleGetDto;
import org.matusikl.dto.roledto.RolePostDto;
import org.matusikl.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @Operation(summary = "Get role", description = "Get role with specified id", tags = "Role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found role with specified id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoleGetDto.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content)})
    @GetMapping(path = "/roles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleGetDto> getRole(@PathVariable("id") Integer id){
        logger.debug("In RoleController getRole() id: {}", id);
        RoleGetDto role = roleService.getRole(id);
        logger.info("Got role: {} id: {} from service", role, id);
        return ResponseEntity
                .ok()
                .body(role);
    }

    @Operation(summary = "Get roles", description = "Get list of roles", tags = "Role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found list of roles",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoleGetDto.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content)})
    @GetMapping(path = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RoleGetDto>> getRoles(){
        logger.debug("In RoleController getRoles()");
        List<RoleGetDto> roleList = roleService.getRoles();
        logger.info("Got list of roles from service");
        return ResponseEntity
                .ok()
                .body(roleList);
    }

    @Operation(summary = "Save role", description = "Save specified role", tags = "Role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saved specified role",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoleGetDto.class))}),
            @ApiResponse(responseCode = "400", description = "Error: validation of attributes",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Error: duplicate - data already exist in database",
                    content = @Content)})
    @PostMapping(path = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleGetDto> addRole(@Valid @RequestBody RolePostDto role){
        logger.debug("In RoleController addRole() role: {}", role);
        RoleGetDto addedRole = roleService.addRole(role);
        logger.info("Added role: {} from service", addedRole);
        return ResponseEntity
                .ok()
                .body(addedRole);
    }

    @Operation(summary = "Delete role", description = "Delete role with specified id", tags = "Role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted role with specified id",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content)})
    @DeleteMapping(path = "/roles/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> deleteRole(@PathVariable ("id") Integer id){
        logger.debug("In RoleController deleteRole() id: {}", id);
        roleService.deleteRole(id);
        logger.info("Deleted role id: {} from service", id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(String.format("Role with id: %d deleted", id));
    }

    @Operation(summary = "Update role", description = "Update role with specified id", tags = "Role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated role with specified id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoleGetDto.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Error: duplicate - data already exist in database",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Error: validation of attributes",
                    content = @Content)})
    @PutMapping(path = "/roles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleGetDto> updateRole(@Valid @RequestBody RolePostDto role,
                                           @PathVariable ("id") Integer id){
        logger.debug("In RoleController updateRole() role: {} id: {}", role, id);
        RoleGetDto updatedRole = roleService.updateRole(role, id);
        logger.info("Updated role: {} id: {} from service", updatedRole, id);
        return ResponseEntity
                .ok()
                .body(updatedRole);
    }
}
