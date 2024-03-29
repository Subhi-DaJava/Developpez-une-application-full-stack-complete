package com.openclassrooms.mddapi.exception.exception_handler;

import com.openclassrooms.mddapi.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    static final String ERROR="error";
    static final String ERROR_ADDRESS="https://mdd.com/errors";

    /**
     * Handle IllegalArgumentException
     * @param ex IllegalArgumentException
     * @return Http Code Status 400 Bad Request
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgumentException(IllegalArgumentException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("Illegal Argument Exception");
        problemDetail.setType(URI.create(ERROR_ADDRESS));
        problemDetail.setProperty(ERROR, ex.getMessage());

        return problemDetail;
    }

    /**
     * Handle FieldShouldNotBeEmptyException
     * @param ex FieldShouldNotBeEmptyException
     * @return Http Code Status 400 Bad Request
     */
    @ExceptionHandler(FieldShouldNotBeEmptyException.class)
    public ProblemDetail handleFiledNotNullOrEmptyException(FieldShouldNotBeEmptyException ex){
        ProblemDetail problemDetail=ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,ex.getMessage());
        problemDetail.setTitle("Fields can not be null or empty");
        problemDetail.setType(URI.create(ERROR_ADDRESS));
        problemDetail.setProperty(ERROR,"Fields can not be NULL or EMPTY");
        return problemDetail;
    }

    /**
     * Handle ResourceNotFoundException
     * @param ex ResourceNotFoundException
     * @return Http Code Status 404 Isn't Found
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException ex){
        ProblemDetail problemDetail=ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,ex.getMessage());
        problemDetail.setTitle("Resource not found with given Id,username or email.");
        problemDetail.setType(URI.create(ERROR_ADDRESS));
        problemDetail.setProperty(ERROR,"Resource not found with given ID, USERNAME or EMAIL.");
        return problemDetail;
    }

    /**
     * Handle UsernameAlreadyExistingException
     * @param ex UsernameAlreadyExistingException
     * @return Http Code Status 409 Conflict
     */
    @ExceptionHandler(UsernameAlreadyExistingException.class)
    public ProblemDetail usernameAlreadyExistingException(UsernameAlreadyExistingException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problemDetail.setTitle("Username has already been taken");
        problemDetail.setType(URI.create(ERROR_ADDRESS));
        problemDetail.setProperty(ERROR, "Username has already been taken");
        return problemDetail;
    }

    /**
     * Handle EmailAlreadyExistingException
     * @param ex EmailAlreadyExistingException
     * @return Http Code Status 409 Conflict
     */
    @ExceptionHandler(EmailAlreadyExistingException.class)
    public ProblemDetail emailAlreadyExistingException(EmailAlreadyExistingException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problemDetail.setTitle("Email has already been taken");
        problemDetail.setType(URI.create(ERROR_ADDRESS));
        problemDetail.setProperty(ERROR, "Email has already been taken");
        return problemDetail;
    }

    /**
     * Handle UserAlreadySubscribeToTopicException
     * @param ex UserAlreadySubscribeToTopicException
     * @return Http Code Status 400 Bad Request
     */
    @ExceptionHandler(UserAlreadySubscribeToTopicException.class)
    public ProblemDetail handleUserAlreadyExistsException(UserAlreadySubscribeToTopicException ex) {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        errorDetail.setTitle("User already subscribed to topic");
        errorDetail.setType(URI.create(ERROR_ADDRESS));
        errorDetail.setProperty(ERROR, "User already subscribed to topic");
        return errorDetail;
    }

    /**
     * Handle UserNotSubscribeToTopicException
     * @param ex UserNotSubscribeToTopicException
     * @return Http Code Status 400 Bad Request
     */
    @ExceptionHandler(UserNotSubscribeToTopicException.class)
    public ProblemDetail handleUserNotSubscribeToTopicException(UserNotSubscribeToTopicException ex) {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        errorDetail.setTitle("User not yet subscribed to topic");
        errorDetail.setType(URI.create(ERROR_ADDRESS));
        errorDetail.setProperty(ERROR, "User not yet subscribed to topic");
        return errorDetail;
    }

}