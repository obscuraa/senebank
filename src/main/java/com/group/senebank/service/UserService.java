package com.group.senebank.service;

import com.group.senebank.dto.users.AuthorizeUserDto;
import com.group.senebank.dto.users.CreateUserDto;
import com.group.senebank.model.User;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public interface UserService {
    User registerUser(CreateUserDto createUserDto, HttpServletResponse response);

    User getUserById(UUID id);

    User getUserByEmail(String email);

    void deleteUser(UUID id);

    String authorizeUser(AuthorizeUserDto authorizeUserDto);
}
