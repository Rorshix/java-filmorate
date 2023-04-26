package ru.yandex.practicum.filmorate.exception;

import org.springframework.http.HttpStatus;

public class ValidationExceptionHttp extends RuntimeException {
	public ValidationExceptionHttp(HttpStatus badRequest, String s) {
		super(s);
	}
}