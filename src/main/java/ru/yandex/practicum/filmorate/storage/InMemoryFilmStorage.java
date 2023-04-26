package ru.yandex.practicum.filmorate.storage;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.FilmNotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validator.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("InMemoryFilmStorage")
public class InMemoryFilmStorage implements FilmStorage {
    private final Map<Integer, Film> films = new HashMap<>();
    private static final LocalDate DATE_FIRST_RELEASE = LocalDate.of(1895, 12, 28);
    private int id;

    @Override
    public Film addFilm(Film film) {
        Validator.validateFilm(film);
        if (!film.getReleaseDate().isBefore(DATE_FIRST_RELEASE)) {
            id++;
            film.setId(id);
            films.put(film.getId(), film);
        } else {
            throw new ValidationException(HttpStatus.BAD_REQUEST, "Дата релиза не может быть раньше 28 декабря 1895 года");
        }
        return film;
    }

    @Override
    public Film updateFilm(Integer id, Film film) {
        Validator.validateFilm(film);
        if (films.containsKey(id)) {
            film.setId(id);
            films.put(id, film);
        } else {
            throw new FilmNotFoundException("Такого фильма нет");
        }
        return film;
    }

    @Override
    public Film deleteFilm(Integer id) {
        if (!films.containsKey(id)) {
            throw new FilmNotFoundException("Такого фильма нет");
        } else {
            Film removeFilm = films.get(id);
            films.remove(id);
            return removeFilm;
        }
    }

    @Override
    public Film getFilm(Integer id) {
        if (films.containsKey(id)) {
            return films.get(id);
        } else {
            throw new FilmNotFoundException("Такого фильма нет");
        }
    }

    @Override
    public List<Film> getListFilms() {
        return new ArrayList<>(films.values());
    }

}