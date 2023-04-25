package ru.yandex.practicum.filmorate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.service.ErrorMessage;

import javax.validation.ValidationException;

@RestControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage handleException(ValidationException exception) {
        return new ErrorMessage("message", exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleException(NotFoundException exception) {
        return new ErrorMessage("message", exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleException(MethodArgumentNotValidException exception) {
        ErrorMessage messages = new ErrorMessage();
        exception.getBindingResult().getAllErrors().forEach(e -> {
            String field = ((FieldError) e).getField();
            String message = e.getDefaultMessage();
            messages.setMessage(field, message);
        });
        return messages;
    }

}