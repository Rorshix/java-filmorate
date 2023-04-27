package ru.yandex.practicum.filmorate.exception;


import lombok.Data;

@Data
public class ErrorMessage {
    private final String error;
}