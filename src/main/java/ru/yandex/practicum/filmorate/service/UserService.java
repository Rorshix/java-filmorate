package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.exception.FilmsAndUsersValidationException;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
public class UserService {
    @Autowired
    public UserStorage userStorage;

    public User get(int userId) {
        final User user = userStorage.get(userId);
        if (user == null) {
            throw new ValidationException("User with id= " + userId + "not found");
        }
        return user;
    }

    public List<User> getAllUsers() {
        return userStorage.getAllUsersList();
    }

    public User save(User user) {
        validateUser(user);
        return userStorage.save(user);
    }

    public boolean checkId(int userId, int friendId) {
        User firstUser = get(userId);
        return firstUser.getFriendsId().contains(friendId);
    }

    public void validateUser(User user) throws IllegalArgumentException {
        if (user.getBirthday().isAfter(LocalDate.now())) {
            throw new FilmsAndUsersValidationException("Не верная дата рождения. Дата не может быть в будущем.");
        }
        if (user.getLogin().isEmpty()) {
            throw new FilmsAndUsersValidationException("Не верный логин. Логин не может быть пустым.");
        }
        if (user.getEmail().isEmpty() || !user.getEmail().contains("@") || user.getEmail().isBlank()) {
            throw new FilmsAndUsersValidationException("Не верный адрес электронной почты." +
                    " Адрес должен содержать символ '@' и не должены быть пустым.");
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
    }
}