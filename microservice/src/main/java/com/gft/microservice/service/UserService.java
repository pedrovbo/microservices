package com.gft.microservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.microservice.dto.UserDTO;
import com.gft.microservice.model.User;
import com.gft.microservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    private static final String USER_NOT_FOUND_WITH_ID = "User not found with id: ";

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getUsers() {
        log.error("getUsers");
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            log.error(USER_NOT_FOUND_WITH_ID + id);
            return new RuntimeException(USER_NOT_FOUND_WITH_ID + id);
        });
    }

    public User createUser(UserDTO userDTO) {
        User user = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .build();
        return userRepository.save(user);
    }

    public User update(Long id, UserDTO userDTO) {

        User user = userRepository.findById(id).orElseThrow(() -> {
            log.error(USER_NOT_FOUND_WITH_ID + id);
            return new RuntimeException(USER_NOT_FOUND_WITH_ID + id);
        });

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        return userRepository.save(user);
    }

    public User delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            log.error(USER_NOT_FOUND_WITH_ID + id);
            return new RuntimeException(USER_NOT_FOUND_WITH_ID + id);
        });

        userRepository.delete(user);

        return user;
    }

}
