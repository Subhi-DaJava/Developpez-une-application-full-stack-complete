package com.openclassrooms.mddapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Username has already been taken", value = HttpStatus.CONFLICT)
public class UsernameAlreadyExistingException extends RuntimeException {
    public UsernameAlreadyExistingException(String usernameHasAlreadyBeenTaken) {
        super(usernameHasAlreadyBeenTaken);
    }
}
