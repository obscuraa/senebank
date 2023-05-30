package com.group.senebank.mapper;

import com.group.senebank.dto.account.FullAccountDto;
import com.group.senebank.model.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    FullAccountDto toFullDto(Account account);
}
