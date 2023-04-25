package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;


import javax.validation.Valid;
import java.util.List;

    @Slf4j
    @RequiredArgsConstructor
    @RestController
    @RequestMapping("/films")
    public class FilmController {
        private final FilmService filmService;

        @GetMapping
        public List<Film> getAllFilms() {
            log.debug("Получен запрос GET /films.");
            return filmService.getAllFilms();
        }

        @GetMapping("/{id}")
        public Film getFilm(@PathVariable Integer id) {
            log.debug("Получен запрос GET /films/{id}");
            return filmService.getFilmById(Long.valueOf(id));
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Film create(@Valid @RequestBody Film film) {
            log.debug("Получен запрос POST /films.");
            log.debug("Фильм успешно создан!");
            return filmService.putFilm(film);
        }

        @PutMapping
        public Film updateFilm(@Valid @RequestBody Film film) {
            log.debug("Получен запрос PUT /films.");

            log.debug("Фильм успешно обновлен!");
            return filmService.updateFilm(film);
        }

    }

