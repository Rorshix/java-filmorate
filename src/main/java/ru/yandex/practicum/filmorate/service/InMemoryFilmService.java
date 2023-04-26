package ru.yandex.practicum.filmorate.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.FilmNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.List;

@Service
public class InMemoryFilmService implements FilmService {
	private final FilmStorage filmStorage;

	public InMemoryFilmService(FilmStorage filmStorage) {
		this.filmStorage = filmStorage;
	}

	@Override
	public Film addFilm(Film newFilm) {
		Film film = filmStorage.addFilm(newFilm);
		if (film == null) {
			throw new FilmNotFoundException("Такого фильма нет");
		}
		validateFilmReleaseDate(newFilm.getReleaseDate());
		return filmStorage.addFilm(newFilm);
	}

	@Override
	public Film updateFilm(Film film) throws ChangeSetPersister.NotFoundException {
		if (filmStorage.getFilm(film.getId()) == null) {
			throw new FilmNotFoundException("Такого фильма нет");
		}
		validateFilmReleaseDate(film.getReleaseDate());
		return filmStorage.updateFilm(film.getId(), film);
	}

	@Override
	public Film deleteFilm(Integer id) throws ChangeSetPersister.NotFoundException {
		Film film = filmStorage.deleteFilm(id);
		if (film == null) {
			throw new FilmNotFoundException("Такого фильма нет");
		}

		return film;
	}

	@Override
	public Film getFilm(Integer id) {
		Film film = filmStorage.getFilm(id);
		if (film == null) {
			throw new FilmNotFoundException("Такого фильма нет");
		}

		return film;
	}

	@Override
	public List<Film> getListFilms() {
		return filmStorage.getListFilms();
	}

	private void validateFilmReleaseDate(LocalDate date) {
		if (date.isBefore(LocalDate.of(1895, 12, 28))) {
			throw new ValidationException("Дата релиза не может быть раньше 28 декабря 1895 года");
		}
	}
}
