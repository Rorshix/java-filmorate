package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import javax.validation.Valid;
import java.util.*;

@RestController
@Slf4j
@RequestMapping("films")
public class FilmController {

    @Autowired
    public FilmService filmService;

    @GetMapping
    public List<Film> getFilms() {
        log.info("Получен запрос. Список всех фильмов");
        return filmService.getAllFilms();
    }

    @GetMapping("{id}")
    public Film filmOnId(@PathVariable int id) {
        log.info("Получен запрос. Фильм с ID: " + id);
        return filmService.getFilmOnId(id);
    }

    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film) {
        log.info("Добавлен фильм: " +
                film.getName() + " ID: " + film.getId() + " Описание: " +
                film.getDescription() + " Дата выхода: " + film.getReleaseDate() +
                " Продолжительность: " + film.getDuration());
        return filmService.createFilm(film);
    }

    @PutMapping
    public Film updateUser(@Valid @RequestBody Film film) {
        filmService.getFilmOnId(film.getId());
        filmService.createFilm(film);
        log.info("Обновлены данные по фильму: " +
                film.getName() + " ID: " + film.getId() + " Описание: " +
                film.getDescription() + " Дата выхода: " + film.getReleaseDate() +
                " Продолжительность: " + film.getDuration());
        return film;
    }

}