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
                .orElseThrow(() -> new DataNotFoundException(String.format("Get role failed! Can't find role with id: %d in database!", id)));
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
            throw new DataDuplicateException(String.format("Added role failed! Role with name: %s already exist!", role.getRole()));
        }
        else{
            Role addedRole = roleRepository.save(role);
            return addedRole;
        }
    }

    @Transactional
    public void deleteRole(Integer id){
        if(roleRepository.existsById(id)){
            roleRepository.deleteById(id);
        }
        else{
            throw new DataNotFoundException(String.format("Delete role failed! Role with id: %d not exist in database!", id));
        }
    }

    @Transactional
    public Role updateRole(Role role, Integer id){
        Role roleDB = roleRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException(String.format("Updated role failed! Cannot find role with id: %d in database!", id)));

        boolean roleExist = roleRepository
                .findByRole(role.getRole())
                .isPresent();
        if(roleExist){
            throw new DataDuplicateException(String.format("Updated role failed! Role with name: %s already exist in database!",role.getRole()));
        }
        else{
            roleDB.setRole(role.getRole());
            roleRepository.save(roleDB);
            return roleDB;
        }
    }
}
