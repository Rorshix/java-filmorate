package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmService {
    Film addFilm(Film film);
    Film updateFilm(Film film);
    Film deleteFilm(Integer id);
    Film getFilm(Integer id);
    List<Film> getListFilms();

}