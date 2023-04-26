package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryFilmStorage implements FilmStorage {

    private int generator = 0;
    private final Map<Integer, Film> allFilms = new HashMap<>();

    @Override
    public Film get(int filmId) {
        return allFilms.get(filmId);
    }

    @Override
    public Film save(Film film) {
        if (!allFilms.containsKey(film.getId())) {
            film.setId(++generator);
        }
        allFilms.put(film.getId(), film);
        return film;
    }
    @Override
    public List<Film> getAllFilms() {
        return new ArrayList<>(allFilms.values());
    }
}