package org.matusikl.mapperinterface;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.matusikl.dto.accountdto.AccountPostDto;
import org.matusikl.dto.accountdto.AccountGetDto;
import org.matusikl.model.Account;

@Mapper(componentModel = "spring")
public interface AccountIMapper {

    AccountIMapper INSTANCE = Mappers.getMapper( AccountIMapper.class );

    AccountGetDto accountToAccountGetDto(Account account);

    Account accountPostDtoToAccount(AccountPostDto accountPostDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAccountFromAccountPostDto(AccountPostDto accountPostDto, @MappingTarget Account account);

}
