package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserStorage {
    User get(int userId);

    List<User> getAllUsersList();

    User save(User user);

}
