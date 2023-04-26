package ru.yandex.practicum.filmorate.storage;


import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmStorage {
    Film addFilm(Film film);

    Film updateFilm(Integer id, Film film);

    Film deleteFilm(Integer id);

    Film getFilm(Integer id);

    List<Film> getListFilms();

}