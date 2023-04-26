package ru.yandex.practicum.filmorate.exception;

public class MissingException extends RuntimeException {
    public MissingException(String message) {
        super(message);
    }
}