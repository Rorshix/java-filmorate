package ru.yandex.practicum.filmorate.storage;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.exception.ValidationExceptionHttp;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validator.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("InMemoryUserStorage")
public class InMemoryUserStorage implements UserStorage {
    private final Map<Integer, User> users = new HashMap<>();
    private int id;

    @Override
    public User addUser(User user) {
        Validator.validateUser(user);
        if ((user.getName() == null) || (user.getName().isBlank())) {
            user.setName(user.getLogin());
        }
        if (!user.getLogin().contains(" ")) {
            id++;
            user.setId(id);
            users.put(user.getId(), user);
        } else {
            throw new ValidationExceptionHttp(HttpStatus.BAD_REQUEST, "Логин не может содержать пробелы");
        }
        return user;
    }

    @Override
    public User updateUser(Integer id, User user) {
        Validator.validateUser(user);
        if (users.containsKey(id)) {
            user.setId(id);
            users.put(id, user);
        } else {
            throw new UserNotFoundException("Такого пользователя нет");
        }
        return user;
    }

    @Override
    public User delUser(Integer id) {
        if (!users.containsKey(id)) {
            throw new UserNotFoundException("Такого пользователя нет");
        } else {
            User remoteUser = users.get(id);
            users.remove(id);
            return remoteUser;
        }
    }

    @Override
    public User getUser(Integer id) {
        if (!users.containsKey(id)) {
            throw new UserNotFoundException("Такого пользователя нет");
        } else {
            return users.get(id);
        }
    }

    @Override
    public List<User> getListUsers() {
        return new ArrayList<>(users.values());
    }


    @Override
    public User getByEmail(String email) {
        return null;
    }
}

