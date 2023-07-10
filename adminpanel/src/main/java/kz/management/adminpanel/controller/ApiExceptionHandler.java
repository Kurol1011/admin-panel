package kz.management.adminpanel.controller;


import io.jsonwebtoken.ExpiredJwtException;
import kz.management.adminpanel.exception.CustomExceptionResponse;
import kz.management.adminpanel.exception.RespondentPostNotFound;
import kz.management.adminpanel.exception.UserIsAlreadyExistsException;
import kz.management.adminpanel.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<CustomExceptionResponse> handleException(UserNotFoundException e){
        CustomExceptionResponse response = new CustomExceptionResponse(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {RespondentPostNotFound.class})
    public ResponseEntity<CustomExceptionResponse> handleException(RespondentPostNotFound e){
        CustomExceptionResponse response = new CustomExceptionResponse(e.getMessage(),LocalDateTime.now());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UserIsAlreadyExistsException.class})
    public ResponseEntity<CustomExceptionResponse> handleException(UserIsAlreadyExistsException e){
        CustomExceptionResponse response = new CustomExceptionResponse(e.getMessage(),LocalDateTime.now());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ExpiredJwtException.class})
    public ResponseEntity<CustomExceptionResponse> handleException(ExpiredJwtException e){
        CustomExceptionResponse response = new CustomExceptionResponse(e.getMessage(),LocalDateTime.now());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
