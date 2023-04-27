package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.exception.FilmsAndUsersValidationException;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.time.LocalDate;
import java.util.*;

@Service
public class FilmService {
    @Autowired
    public UserStorage userStorage;

    @Autowired
    public FilmStorage filmStorage;

    private static final int MAX_DESCRIPTION_SIMBOLS = 200;
    private static final LocalDate FIRST_FILM_DATE = LocalDate.of(1895, 12, 28);

    public List<Film> getAllFilms() {
        return filmStorage.getAllFilms();
    }

    public Film getFilmOnId(int filmId) {
        Film film = filmStorage.get(filmId);
        if (film == null) {
            throw new ValidationException("User with id= " + filmId + "not found");
        }
        return filmStorage.get(filmId);
    }

    public Film createFilm(Film film) {
        validateFilm(film);
        return filmStorage.save(film);
    }

    public boolean checkId(int filmId, int userId) {
        Film film = filmStorage.get(filmId);
        if (userStorage.get(userId) == null) {
            throw new ValidationException("User with id= " + userId + "not found");
        }
        return film.getFilmsLike().contains(userId);
    }

    public void validateFilm(Film film) throws FilmsAndUsersValidationException {
        if (film.getDescription().length() > MAX_DESCRIPTION_SIMBOLS) {
            throw new FilmsAndUsersValidationException("Описание фильма больше 200 символов. " +
                    "Необходимо сократить описание.");
        }
        if (film.getReleaseDate().isBefore(FIRST_FILM_DATE)) {
            throw new FilmsAndUsersValidationException("Не верная дата выхода фильма. " +
                    "Дата выхода фильма должна быть не раньше, чем 28 декабря 1895 года.");
        }
        if (film.getName() == null || film.getName().isEmpty() || film.getName().isBlank()) {
            throw new FilmsAndUsersValidationException("Не верное название. Название фильма не может быть пустым.");
        }
        if (film.getDuration() < 0) {
            throw new FilmsAndUsersValidationException("Не верная продолжительность фильма. " +
                    "Продолжительность фильма не может быть меньше 0.");
        }
    }
}