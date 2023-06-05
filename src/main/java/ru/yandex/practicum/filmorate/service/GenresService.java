package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dao.GenresDaoImpl;
import ru.yandex.practicum.filmorate.model.Genres;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class GenresService {

    private final GenresDaoImpl genresDao;

    public Collection<Genres> getGenres() {
        return genresDao.getGenres();
    }

    public Genres getGenresById(int id) {
        return genresDao.getGenresById(id);
    }
}