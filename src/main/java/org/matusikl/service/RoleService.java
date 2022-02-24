package org.matusikl.service;

import org.matusikl.dto.roledto.RoleGetDto;
import org.matusikl.dto.roledto.RolePostDto;
import org.matusikl.exception.DataDuplicateException;
import org.matusikl.exception.DataNotFoundException;
import org.matusikl.mapperinterface.RoleIMapper;
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
    RoleIMapper roleIMapper;
    private Logger logger = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    public RoleService(RoleRepository roleRepository,
                       RoleIMapper roleIMapper){
        this.roleRepository = roleRepository;
        this.roleIMapper = roleIMapper;
    }

    public RoleGetDto getRole(Integer id){
        logger.debug("In RoleService getRole() method id: {}", id);
        Role role = roleRepository
                .findById(id)
                .orElseThrow(() -> {
                        DataNotFoundException exception = new DataNotFoundException(String.format("Get role failed! Can't find role with id: %d in database!", id));
                        logger.error("Exception occured in getRole() id: {}", id, exception);
                        throw exception;
                });
        logger.info("Found role: {} with id: {}", role, id);
        RoleGetDto roleGetDTO = roleIMapper.roleToRoleGetDto(role);
        return roleGetDTO;
    }

    public List<RoleGetDto> getRoles(){
        logger.debug("In RoleService getRoles() method");
        List<Role> roleList = roleRepository.findAll();
        if(roleList.isEmpty()){
            DataNotFoundException exception =  new DataNotFoundException("Get roles failed! There are no roles in database");
            logger.error("Exception occured in getRoles()", exception);
            throw exception;
        }
        else{
            logger.info("Found list of roles");
            List<RoleGetDto> roleGetDtoList = roleIMapper.listRoleToListRoleGetDto(roleList);
            return roleGetDtoList;
        }
    }

    @Transactional
    public RoleGetDto addRole(RolePostDto rolePostDto){
        logger.debug("In RoleService addRole() method role: {}", rolePostDto);
        Role role = roleIMapper.rolePostDtoToRole(rolePostDto);
        boolean roleExist = roleRepository
                .findByRole(role.getRole())
                .isPresent();
        if(roleExist){
            DataDuplicateException exception = new DataDuplicateException(String.format("Added role failed! Role with name: %s already exist!", role.getRole()));
            logger.error("Exception occured in addRole() role: {}", role, exception);
            throw exception;
        }
        else{
            Role roleDB = roleRepository.save(role);
            logger.info("Role {} added successfully", roleDB);
            RoleGetDto roleGetDto = roleIMapper.roleToRoleGetDto(roleDB);
            return roleGetDto;
        }
    }

    @Transactional
    public void deleteRole(Integer id){
        logger.debug("In RoleService deleteRole() method id: {}", id);
        if(roleRepository.existsById(id)){
            roleRepository.deleteById(id);
            logger.info("Role id: {} deleted successfully", id);
        }
        else{
            DataNotFoundException exception = new DataNotFoundException(String.format("Delete role failed! Role with id: %d not exist in database!", id));
            logger.error("Exception occured in deleteRole() id: {} ", id, exception);
            throw exception;
        }
    }

    @Transactional
    public RoleGetDto updateRole(RolePostDto rolePostDto, Integer id){
        logger.debug("In RoleService updateRole() method role: {} id: {}", rolePostDto, id);
        Role role = roleIMapper.rolePostDtoToRole(rolePostDto);
        Role roleDB = roleRepository
                .findById(id)
                .orElseThrow(() ->{
                        DataNotFoundException exception = new DataNotFoundException(String.format("Updated role failed! Cannot find role with id: %d in database!", id));
                        logger.error("Error occured in updateRole() findById() role: {} id: {}", role, id, exception);
                        throw exception;
                });

        boolean roleExist = roleRepository
                .findByRole(role.getRole())
                .isPresent();

        if(roleExist){
            DataDuplicateException exception = new DataDuplicateException(String.format("Updated role failed! Role with name: %s already exist in database!",role.getRole()));
            logger.error("Error occured in updateRole() findByRole() role: {} id: {}", roleDB, id, exception);
            throw exception;
        }
        else{
            roleIMapper.updateRoleFromRolePostDto(rolePostDto, roleDB);
            roleRepository.save(roleDB);
            logger.info("Role: {} id: {} updated successfully", roleDB, id);
            RoleGetDto roleGetDto = roleIMapper.roleToRoleGetDto(roleDB);
            return roleGetDto;
        }
    }
}
