package com.group.senebank.service.impl;

import com.group.senebank.dao.UserDao;
import com.group.senebank.dto.users.AuthorizeUserDto;
import com.group.senebank.dto.users.CreateUserDto;
import com.group.senebank.mapper.UsersMapper;
import com.group.senebank.model.User;
import com.group.senebank.service.UserService;
import com.group.senebank.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final UsersMapper usersMapper;

    @Override
    public User registerUser(CreateUserDto createUserDto, HttpServletResponse response) {
        User user = usersMapper.createDtoToEntity(createUserDto);
        user.setRegistrationDate(LocalDateTime.now());
        response.addHeader("Authorization", generateJwt(createUserDto.getEmail(), createUserDto.getRole()));
        return userDao.saveUser(user);
    }

    @Override
    public User getUserById(UUID id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Override
    public void deleteUser(UUID id) {
        userDao.deleteUserById(id);
    }

    @Override
    public String authorizeUser(AuthorizeUserDto authorizeUserDto) {
        User user = userDao.findUserByEmail(authorizeUserDto.getEmail());
        return generateJwt(user.getEmail(), user.getRole());
    }

    private String generateJwt(String email, String role) {
        return "Bearer " + TokenUtils.generateToken(email, role);
    }
}
