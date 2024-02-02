package com.openclassrooms.mddapi.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(value = BAD_REQUEST, reason = "User not subscribed to topic")
public class UserNotSubscribeToTopicException extends RuntimeException {
    public UserNotSubscribeToTopicException(String userNotSubscribedToTopic) {
        super(userNotSubscribedToTopic);
    }
}
