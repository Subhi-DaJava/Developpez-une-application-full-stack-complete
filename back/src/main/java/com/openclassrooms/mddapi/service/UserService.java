package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.UserRequest;
import com.openclassrooms.mddapi.dto.UserResponse;
import com.openclassrooms.mddapi.exception.EmailAlreadyExistingException;
import com.openclassrooms.mddapi.exception.FieldShouldNotBeEmptyException;
import com.openclassrooms.mddapi.exception.ResourceNotFoundException;
import com.openclassrooms.mddapi.exception.UsernameAlreadyExistingException;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse getUserByUserName(String username) {
        User user = retrieveUserWithTopicsByUsername(username);

        UserResponse userResponse = userMapper.userToUserResponse(user);

        log.info("User found: {}", userResponse);
        return userResponse;
    }

    @Override
    public UserResponse addUser(UserRequest userRequest) {

        if (userRequest.getUsername() == null || userRequest.getUsername().isEmpty() ||
                userRequest.getPassword() == null || userRequest.getPassword().isEmpty() ||
                userRequest.getEmail() == null || userRequest.getEmail().isEmpty()) {
            log.error("Field should not be empty");
            throw new FieldShouldNotBeEmptyException("Field should not be empty");
        }

        User existingUserByUsername = userRepository.findByUsername(userRequest.getUsername()).orElse(null);
        User existingUserByEmail = userRepository.findByEmail(userRequest.getEmail()).orElse(null);

        if (existingUserByUsername != null) {
            log.error("Username already exists in db");
            throw new UsernameAlreadyExistingException("Username already exists in db");
        }

        if (existingUserByEmail != null) {
            log.error("Email already exists in db");
            throw new EmailAlreadyExistingException("Email already exists in db");
        }

        User user = userMapper.userRequestToUser(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        User savedUser = userRepository.save(user);
        UserResponse userResponse = userMapper.userToUserResponse(savedUser);
        log.info("User added: {}", userResponse);
        return userResponse;
    }

    @Override
    public void updateUser(String username, UserRequest userRequest) throws UsernameAlreadyExistingException, EmailAlreadyExistingException {
        User userToUpdate = retrieveUserByUsername(username);

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

    private User retrieveUserWithTopicsByUsername(String username) {
        return userRepository.findWithTopicsByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username:{%s} ".formatted(username)));
    }

    private User retrieveUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username:{%s} ".formatted(username)));
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
