package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserStorage {
    List<User> getAllUsers();

    void removeAllUsers();

    User getUserById(Integer id);

    User putUser(User user);

    User updateUser(User user);

    void removeUserById(Integer id);
}