package org.matusikl.service;

import org.matusikl.exception.DataDuplicateException;
import org.matusikl.exception.DataNotFoundException;
import org.matusikl.model.Role;
import org.matusikl.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RoleService {

    RoleRepository roleRepository;
    private Logger logger = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public Role getRole(Integer id){
        logger.debug("In RoleService getRole() method");
        Role role = roleRepository
                .findById(id)
                .orElseThrow(() -> {
                        DataNotFoundException exception = new DataNotFoundException(String.format("Get role failed! Can't find role with id: %d in database!", id));
                        logger.error("Exception occured in getRole() id: {}", id, exception);
                        throw exception;
                });
        logger.info("Found role with id {}", id);
        return role;
    }

    public List<Role> getRoles(){
        logger.debug("In RoleService getRoles() method");
        List<Role> roleList = roleRepository.findAll();
        if(roleList.isEmpty()){
            DataNotFoundException exception =  new DataNotFoundException("Get roles failed! There are no roles in database");
            logger.error("Exception occured in getRoles()", exception);
            throw exception;
        }
        else{
            logger.info("Found list of roles");
            return roleList;
        }
    }

    @Transactional
    public Role addRole(Role role){
        logger.debug("In RoleService addRole() method");
        boolean roleExist = roleRepository
                .findByRole(role.getRole())
                .isPresent();
        if(roleExist){
            DataDuplicateException exception = new DataDuplicateException(String.format("Added role failed! Role with name: %s already exist!", role.getRole()));
            logger.error("Exception occured in addRole(): {}", exception);
            throw exception;
        }
        else{
            Role roleDB = roleRepository.save(role);
            logger.info("Role {} added successfully", roleDB);
            return roleDB;
        }
    }

    @Transactional
    public void deleteRole(Integer id){
        logger.debug("In RoleService deleteRole() method");
        if(roleRepository.existsById(id)){
            roleRepository.deleteById(id);
            logger.info("Role id {} deleted successfully", id);
        }
        else{
            DataNotFoundException exception = new DataNotFoundException(String.format("Delete role failed! Role with id: %d not exist in database!", id));
            logger.error("Exception occured in deleteRole() id: {} ", id, exception);
            throw exception;
        }
    }

    @Transactional
    public Role updateRole(Role role, Integer id){
        logger.debug("In RoleService updateRole() method");
        Role roleDB = roleRepository
                .findById(id)
                .orElseThrow(() ->{
                        DataNotFoundException exception = new DataNotFoundException(String.format("Updated role failed! Cannot find role with id: %d in database!", id));
                        logger.error("Error occured in updateRole() findById()", exception);
                        throw exception;
                });

        boolean roleExist = roleRepository
                .findByRole(role.getRole())
                .isPresent();
        if(roleExist){
            DataDuplicateException exception = new DataDuplicateException(String.format("Updated role failed! Role with name: %s already exist in database!",role.getRole()));
            logger.error("Error occured in updateRole() findByRole()", exception);
            throw exception;
        }
        else{
            roleDB.setRole(role.getRole());
            roleRepository.save(roleDB);
            logger.info("Role id {} updated successfully", id);
            return roleDB;
        }
    }
}
