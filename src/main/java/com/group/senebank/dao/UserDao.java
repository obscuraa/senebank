package com.group.senebank.dao;

import com.group.senebank.exception.NotFoundException;
import com.group.senebank.model.User;
import com.group.senebank.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.group.senebank.util.ErrorMessages.USER_ERROR_MESSAGE;
import static com.group.senebank.util.ErrorMessages.USER_NOT_FOUND_BY_EMAIL;

@Component
@RequiredArgsConstructor
public class UserDao {

    private final UserRepository userRepository;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_BY_EMAIL, email));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_ERROR_MESSAGE, id));
    }

}
