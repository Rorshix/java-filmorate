package ru.yandex.practicum.filmorate.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage handleNotFoundException(MissingException notFoundException) {
		log.info("404 {}", notFoundException.getMessage());
		return new ErrorMessage(notFoundException.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handleBadRequestException(FilmsAndUsersValidationException validationException) {
		log.info("400 {}", validationException.getMessage());
		return new ErrorMessage(validationException.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage handleInternalServerError(ValidationException internalServerError) {
		log.info("500 {}", internalServerError.getMessage());
		return new ErrorMessage(internalServerError.getMessage());
	}
}
