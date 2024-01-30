package com.openclassrooms.mddapi.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(reason = "Field should not be empty", value = BAD_REQUEST)
public class FieldShouldNotBeEmptyException extends RuntimeException {
    public FieldShouldNotBeEmptyException(String fieldShouldNotBeEmpty) {
        super(fieldShouldNotBeEmpty);
    }
}
