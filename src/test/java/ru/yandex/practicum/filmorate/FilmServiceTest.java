package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.exception.FilmsAndUsersValidationException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FilmServiceTest {
    FilmService filmService = new FilmService();

    Film film;

    @BeforeEach
    void createFilmForTest() {
        film = new Film(1, "film1", "good film",
                LocalDate.of(2000, 10, 11), 60);
    }

    @Test
    void shouldCreateFilmWithEmptyName() {
        film.setName("");
        FilmsAndUsersValidationException exception = assertThrows(FilmsAndUsersValidationException.class,
                () -> filmService.validateFilm(film));
        assertEquals("Не верное название. Название фильма не может быть пустым.", exception.getMessage());
    }

    @Test
    void shouldCreateFilmWithOver200SimbolDescription() {
        film.setDescription("a".repeat(201));
        FilmsAndUsersValidationException exception = assertThrows(FilmsAndUsersValidationException.class,
                () -> filmService.validateFilm(film));
        assertEquals("Описание фильма больше 200 символов. " +
                "Необходимо сократить описание.", exception.getMessage());
    }

    @Test
    void shouldCreateFilmWithIncorrectReleaseDate() {
        film.setReleaseDate(LocalDate.of(1000, 10, 11));
        FilmsAndUsersValidationException exception = assertThrows(FilmsAndUsersValidationException.class,
                () -> filmService.validateFilm(film));
        assertEquals("Не верная дата выхода фильма. " +
                "Дата выхода фильма должна быть не раньше, чем 28 декабря 1895 года.", exception.getMessage());
    }

    @Test
    void shouldCreateFilmWithIncorrectDuration() {
        film.setDuration(-60);
        FilmsAndUsersValidationException exception = assertThrows(FilmsAndUsersValidationException.class,
                () -> filmService.validateFilm(film));
        assertEquals("Не верная продолжительность фильма. " +
                "Продолжительность фильма не может быть меньше 0.", exception.getMessage());
    }
}
