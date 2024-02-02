package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.UserRequest;
import com.openclassrooms.mddapi.dto.UserResponse;
import com.openclassrooms.mddapi.exception.EmailAlreadyExistingException;
import com.openclassrooms.mddapi.exception.UsernameAlreadyExistingException;

public interface IUserService {

    UserResponse getUserByUserName(String username);
    UserResponse addUser(UserRequest userRequest);
    void updateUser(String username, UserRequest userRequest) throws UsernameAlreadyExistingException, EmailAlreadyExistingException;

}
