package com.dinelink.exceptionHandlers;

import com.dinelink.entities.ModeratorErrorResponse;
import com.dinelink.exceptions.EmailOrPasswordIncorrectException;
import com.dinelink.exceptions.ModeratorNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ModeratorExceptionHandler {

    // Handles EmailOrPasswordIncorrectException
    @ExceptionHandler(EmailOrPasswordIncorrectException.class)
    public ResponseEntity<ModeratorErrorResponse> handleException(EmailOrPasswordIncorrectException exception) {
        ModeratorErrorResponse error = new ModeratorErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value()); // HTTP 400
        error.setMessage("Either the email or password is incorrect");
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Handles ModeratorNotFoundException
    @ExceptionHandler(ModeratorNotFoundException.class)
    public ResponseEntity<ModeratorErrorResponse> handleException(ModeratorNotFoundException exception) {
        ModeratorErrorResponse error = new ModeratorErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value()); // HTTP 404
        error.setMessage("Moderator not found with the provided email");
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
