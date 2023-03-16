package com.example.boot3testinge1.exception.handler;

import com.example.boot3testinge1.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorMessage> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        return ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> new ErrorMessage(fieldError.getDefaultMessage(), LocalDateTime.now()))
                .collect(toList());

    }

}
