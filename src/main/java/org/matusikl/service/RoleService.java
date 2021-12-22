package org.matusikl.service;

import org.matusikl.exception.DataDuplicateException;
import org.matusikl.exception.DataNotFoundException;
import org.matusikl.model.Role;
import org.matusikl.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RoleService {

    RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public Role getRole(Integer id){
        Role role = roleRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Get role failed! Can't find role with id: " + id + " in database!"));
        return role;
    }

    public List<Role> getRoles(){
        List<Role> roleList = roleRepository.findAll();
        return roleList;
    }

    @Transactional
    public Role addRole(Role role){
        boolean roleExist = roleRepository
                .findByRole(role.getRole())
                .isPresent();
        if(roleExist){
            throw new DataDuplicateException("Added role failed! Role with name: " + role.getRole() + " already exist!");
        }
        else{
            Role addedRole = roleRepository.save(role);
            return addedRole;
        }
    }

    @Transactional
    public void deleteRole(Integer id){
        boolean roleExist = roleRepository.existsById(id);
        if(roleExist){
            roleRepository.deleteById(id);
        }
        else{
            throw new DataNotFoundException("Delete role failed! Role with id: " + id + " not exist in database!");
        }
    }

    @Transactional
    public Role updateRole(Role role, Integer id){
        Role roleDB = roleRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Updated role failed! Cannot find role with id: " + id + " in database!"));

        boolean roleExist = roleRepository
                .findByRole(role.getRole())
                .isPresent();
        if(roleExist){
            throw new DataDuplicateException("Updated role failed! Role with name: " + role.getRole() + " already exist in database!");
        }
        else{
            roleDB.setRole(role.getRole());
            roleRepository.save(roleDB);
            return roleDB;
        }
    }
}
