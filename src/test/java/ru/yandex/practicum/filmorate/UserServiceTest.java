package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.exception.FilmsAndUsersValidationException;
import ru.yandex.practicum.filmorate.service.UserService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    UserService userService;
    User user;

    @Test
    void shouldCreateUserWithIncorrectEmailWithoutDog() {
        user.setEmail("mailmail.com");
        FilmsAndUsersValidationException exception = assertThrows(FilmsAndUsersValidationException.class,
                () -> userService.validateUser(user));
        assertEquals("Не верный адрес электронной почты. " +
                "Адрес должен содержать символ '@' и не должены быть пустым.", exception.getMessage());
    }

    @Test
    void shouldCreateUserWithEmptyEmail() {
        user.setEmail("");
        FilmsAndUsersValidationException exception = assertThrows(FilmsAndUsersValidationException.class,
                () -> userService.validateUser(user));
        assertEquals("Не верный адрес электронной почты. " +
                "Адрес должен содержать символ '@' и не должены быть пустым.", exception.getMessage());
    }

    @Test
    void shouldCreateUserWithoutLogin() {
        user.setLogin("");

        FilmsAndUsersValidationException exception = assertThrows(FilmsAndUsersValidationException.class,
                () -> userService.validateUser(user));
        assertEquals("Не верный логин. Логин не может быть пустым.", exception.getMessage());
    }

    @Test
    void shouldCreateUserWithIncorrectBirthday() {
        user.setBirthday(LocalDate.of(2030, 10, 11));

        FilmsAndUsersValidationException exception = assertThrows(FilmsAndUsersValidationException.class,
                () -> userService.validateUser(user));
        assertEquals("Не верная дата рождения. Дата не может быть в будущем.", exception.getMessage());
    }
}
