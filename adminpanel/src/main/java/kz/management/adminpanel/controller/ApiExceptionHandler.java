package kz.management.adminpanel.controller;


import kz.management.adminpanel.exception.CustomExceptionResponse;
import kz.management.adminpanel.exception.RespondentPostNotFound;
import kz.management.adminpanel.exception.UserNotFoundException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<CustomExceptionResponse> handleException(UserNotFoundException e){
        CustomExceptionResponse response = new CustomExceptionResponse("User not found by email/id", LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {})
    public ResponseEntity<CustomExceptionResponse> handleException(RespondentPostNotFound e){
        CustomExceptionResponse response = new CustomExceptionResponse("Respondent post not found",LocalDateTime.now());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
