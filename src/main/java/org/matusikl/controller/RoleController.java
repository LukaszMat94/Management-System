package org.matusikl.controller;

import org.matusikl.model.Role;
import org.matusikl.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController
public class RoleController {

    RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping(path = "/roles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> getRole(@PathVariable ("id") Integer id){
        Role role = roleService.getRole(id);
        return ResponseEntity
                .ok()
                .body(role);
    }

    @GetMapping(path = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Role>> getRoles(){
        List<Role> roleList = roleService.getRoles();
        return ResponseEntity
                .ok()
                .body(roleList);
    }

    @PostMapping(path = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> addRole(@RequestBody Role role){
        Role addedRole = roleService.addRole(role);
        return ResponseEntity
                .ok()
                .body(addedRole);
    }

    @DeleteMapping(path = "/roles/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> deleteRole(@PathVariable ("id") Integer id){
        roleService.deleteRole(id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(String.format("Role with id: %d deleted", id));
    }

    @PutMapping(path = "/roles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> updateRole(@RequestBody Role role,
                                           @PathVariable ("id") Integer id){
        Role updatedRole = roleService.updateRole(role, id);
        return ResponseEntity
                .ok()
                .body(updatedRole);
    }
}
