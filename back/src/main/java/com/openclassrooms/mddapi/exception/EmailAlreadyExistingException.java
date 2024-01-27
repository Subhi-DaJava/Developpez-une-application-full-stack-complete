package com.openclassrooms.mddapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Email has already been taken", value = HttpStatus.CONFLICT)
public class EmailAlreadyExistingException extends RuntimeException{
    public EmailAlreadyExistingException(String emailHasAlreadyBeenTaken) {
        super(emailHasAlreadyBeenTaken);
    }
}
