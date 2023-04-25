package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryFilmStorage implements FilmStorage {

	private final Map<Long,Film> films = new HashMap<>();
	private Long globalId = 1L;

	@Override
	public List<Film> getAllFilms() {
		return List.copyOf(films.values());
	}

	@Override
	public Film getFilmById(Long id) {
		return films.get(id);
	}

	@Override
	public Film putFilm(Film film) {
		film.setId(Math.toIntExact(generateId()));
		films.put (Long.valueOf(film.getId()), film);
		return film;
	}

	@Override
	public Film updateFilm(Film film) {
		films.put(Long.valueOf(film.getId()), film);
		return film;
	}

	private Long generateId() {
		return globalId++;
	}
}