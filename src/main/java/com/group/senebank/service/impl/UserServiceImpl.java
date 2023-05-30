package com.group.senebank.service.impl;

import com.group.senebank.dto.users.AuthorizeUserDto;
import com.group.senebank.dto.users.CreateUserDto;
import com.group.senebank.exception.NotFoundException;
import com.group.senebank.mapper.UsersMapper;
import com.group.senebank.model.User;
import com.group.senebank.repository.UsersRepository;
import com.group.senebank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.NoSuchElementException;
import java.util.UUID;

import static com.group.senebank.config.ErrorMessages.USER_NOT_FOUND_BY_EMAIL;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final TokenService tokenService;

    @Override
    public User registerUser(CreateUserDto createUserDto, HttpServletResponse response) {
        User user = usersMapper.createDtoToEntity(createUserDto);
        response.addHeader("Authorization", generateJwt(createUserDto.getEmail(), createUserDto.getRole()));
        return usersRepository.save(user);
    }

    @Override
    public User getUserById(UUID id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User id " + id + " not found"));
    }

    @Override
    public User getUserByEmail(String email) {
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("User email " + email + " not found"));
    }

    @Override
    public void deleteUser(UUID id) {
        if(!usersRepository.existsById(id)){
            throw new NoSuchElementException("User id " + id + "not found");
        }
        usersRepository.deleteById(id);
    }

    @Override
    public String authorizeUser(AuthorizeUserDto authorizeUserDto) {
        User user = usersRepository.findByEmail(authorizeUserDto.getEmail())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_BY_EMAIL, authorizeUserDto.getEmail()));
        return generateJwt(user.getEmail(), user.getRole());
    }

    private String generateJwt(String email, String role) {
        return "Bearer " + tokenService.generateToken(email, role);
    }
}
