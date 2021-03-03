package org.embl.co.controller.advice;

import org.embl.co.dto.response.ResponseError;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@ControllerAdvice
public class PersonControllerAdvice {

    @ExceptionHandler(value
            = EmptyResultDataAccessException.class)
    public ResponseEntity<ResponseError> handleEmptyResultDataAccessException(
            EmptyResultDataAccessException e) {
        String error = Optional.ofNullable(e.getMessage()).orElse(e.getClass().getName())
                + " [Internal server exception! => (EmptyResultDataAccessException)]";
        ResponseError errorResponse = ResponseError.builder()
                .createdAt(LocalDateTime.now().toString())
                .detailedMessage(error)
                .errorCode(HttpStatus.NOT_FOUND.value())
                .exceptionName(EmptyResultDataAccessException.class.getName())
                .errorMessage(e.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value
            = NoSuchElementException.class)
    public ResponseEntity<ResponseError> handleNoSuchElementException(
            NoSuchElementException e) {
        String error = Optional.ofNullable(e.getMessage()).orElse(e.getClass().getName())
                + " [Internal server exception! => (NoSuchElementException)]";
        ResponseError errorResponse = ResponseError.builder()
                .createdAt(LocalDateTime.now().toString())
                .detailedMessage(error)
                .errorCode(HttpStatus.NOT_FOUND.value())
                .exceptionName(NoSuchElementException.class.getName())
                .errorMessage(e.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
