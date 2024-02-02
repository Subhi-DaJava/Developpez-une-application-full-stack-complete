package com.openclassrooms.mddapi.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(value = BAD_REQUEST, reason = "User already subscribed to topic")
public class UserAlreadySubscribeToTopicException extends RuntimeException {
    public UserAlreadySubscribeToTopicException(String userAlreadySubscribedToTopic) {
        super(userAlreadySubscribedToTopic);
    }
}
