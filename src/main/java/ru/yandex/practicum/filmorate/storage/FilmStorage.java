package ru.yandex.practicum.filmorate.storage;


import org.springframework.data.crossstore.ChangeSetPersister;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmStorage {
    Film addFilm(Film film);

    Film updateFilm(Integer id, Film film)throws ChangeSetPersister.NotFoundException;

    Film deleteFilm(Integer id) throws ChangeSetPersister.NotFoundException;

    Film getFilm(Integer id);

    List<Film> getListFilms();

}