package com.group.senebank.mapper;

import com.group.senebank.dto.users.CreateUserDto;
import com.group.senebank.dto.users.FullUserDto;
import com.group.senebank.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    User createDtoToEntity(CreateUserDto createUserDto);

    FullUserDto toFullDto(User user);
}
