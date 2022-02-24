package org.matusikl.mapperinterface;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.matusikl.dto.roledto.RoleGetDto;
import org.matusikl.dto.roledto.RolePostDto;
import org.matusikl.model.Role;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleIMapper {

    RoleIMapper INSTANCE = Mappers.getMapper( RoleIMapper.class );

    RoleGetDto roleToRoleGetDto(Role role);

    Role rolePostDtoToRole(RolePostDto rolePostDto);

    List<RoleGetDto> listRoleToListRoleGetDto(List<Role> roleList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRoleFromRolePostDto(RolePostDto rolePostDto, @MappingTarget Role role);
}