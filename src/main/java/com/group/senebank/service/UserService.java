package com.group.senebank.service;

import com.group.senebank.dto.users.CreateUserDto;
import com.group.senebank.model.User;

import java.util.UUID;

public interface UserService {
    User addUser(CreateUserDto createUserDto);

    User findUserById(UUID id);

    User findUserByEmail(String email);

    void deleteUser(UUID id);
}
