package com.group.senebank.controller;

import com.group.senebank.dto.users.AuthorizeUserDto;
import com.group.senebank.dto.users.CreateUserDto;
import com.group.senebank.dto.users.FullUserDto;
import com.group.senebank.mapper.UsersMapper;
import com.group.senebank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;
    private final UsersMapper usersMapper;

    @PostMapping(path = "/authorize")
    public String authorizeUser(@RequestBody AuthorizeUserDto authorizeUserDto) {
        return userService.authorizeUser(authorizeUserDto);
    }

    @PostMapping(path = "/register")
    public FullUserDto registerUser(@RequestBody CreateUserDto createUserDto, HttpServletResponse response) {
        return usersMapper.toFullDto(userService.registerUser(createUserDto, response));
    }

    @DeleteMapping(path = "/{userId}")
    public void deleteUser(@PathVariable("userId") UUID userId){
        userService.deleteUser(userId);
    }
}
