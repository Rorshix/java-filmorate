package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserStorage {
    User addUser(User user);
    User updateUser(Integer id, User user);
    User delUser(Integer id);
    User getUser(Integer id);
    List<User> getListUsers();
    User getByEmail(String email);

}
