package com.group.senebank.controller;

import com.group.senebank.dto.users.CreateUserDto;
import com.group.senebank.dto.users.FullUsersDto;
import com.group.senebank.mapper.UsersMapper;
import com.group.senebank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;
    private final UsersMapper usersMapper;

    public void authorizeUser(){

    }

    @PostMapping
    public FullUsersDto addUser(@RequestBody CreateUserDto createUserDto){
        return usersMapper.toFullDto(userService.addUser(createUserDto));
    }

    @DeleteMapping(path = "{usersId}")
    public void deleteUser(@PathVariable("usersId") UUID userId){
        userService.deleteUser(userId);
    }
}
