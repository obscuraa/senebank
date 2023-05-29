package com.group.senebank.service.impl;

import com.group.senebank.dto.users.CreateUserDto;
import com.group.senebank.mapper.UsersMapper;
import com.group.senebank.model.User;
import com.group.senebank.repository.UsersRepository;
import com.group.senebank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    @Override
    public User addUser(CreateUserDto createUserDto) {
        User user = usersMapper.createDtoToEntity(createUserDto);
        return usersRepository.save(user);
    }

    @Override
    public User findUserById(UUID id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User id " + id + " not found"));
    }

    @Override
    public User findUserByEmail(String email) {
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
}
