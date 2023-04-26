package ru.yandex.practicum.filmorate.exception;

public class FilmsAndUsersValidationException extends IllegalArgumentException {

    public FilmsAndUsersValidationException(String message) {
        super(message);
    }
}
