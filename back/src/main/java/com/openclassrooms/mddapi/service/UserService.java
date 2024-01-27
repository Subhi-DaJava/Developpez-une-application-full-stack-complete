package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.UserRequest;
import com.openclassrooms.mddapi.dto.UserResponse;
import com.openclassrooms.mddapi.exception.EmailAlreadyExistingException;
import com.openclassrooms.mddapi.exception.ResourceNotFoundException;
import com.openclassrooms.mddapi.exception.UsernameAlreadyExistingException;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = retrieveUserById(id);

        UserResponse userResponse = userMapper.userToUserResponse(user);

        log.info("User found: {}", userResponse);
        return userResponse;
    }

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        User user = userMapper.userRequestToUser(userRequest);
        User savedUser = userRepository.save(user);
        UserResponse userResponse = userMapper.userToUserResponse(savedUser);
        log.info("User added: {}", userResponse);
        return userResponse;
    }

    @Override
    public void updateUser(Long userId, UserRequest userRequest) throws UsernameAlreadyExistingException, EmailAlreadyExistingException {
        User userToUpdate = retrieveUserById(userId);

        if (userRequest.getUsername() != null && !userRequest.getUsername().isEmpty()) {
            isUsernameTaken(userRequest, userToUpdate);
            userToUpdate.setUsername(userRequest.getUsername());
        }

        if (userRequest.getEmail() != null && !userRequest.getEmail().isEmpty()) {
            isEmailTaken(userRequest, userToUpdate);
            userToUpdate.setEmail(userRequest.getEmail());
        }

        if (userRequest.getPassword() != null && !userRequest.getPassword().isEmpty()) {
            userToUpdate.setPassword(userRequest.getPassword());
        }

        log.info("User updated: {}", userToUpdate);
        userRepository.save(userToUpdate);
    }

    private User retrieveUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: {%d}".formatted(id)));
    }

    private void isUsernameTaken(UserRequest userRequest, User userToUpdate) throws UsernameAlreadyExistingException {
        User existingUser = userRepository.findByUsername(userRequest.getUsername()).orElse(null);

        if (existingUser != null && !existingUser.getId().equals(userToUpdate.getId())) {
            log.error("Username already taken");
            throw new UsernameAlreadyExistingException("Username has already been taken");
        }
    }

    private void isEmailTaken(UserRequest userRequest, User userToUpdate) throws EmailAlreadyExistingException {
        User existingUser = userRepository.findByEmail(userRequest.getEmail()).orElse(null);

        if (existingUser != null && !existingUser.getId().equals(userToUpdate.getId())) {
            log.error("Email already taken");
            throw new EmailAlreadyExistingException("Email has already been taken");
        }
    }

}
