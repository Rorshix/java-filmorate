package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;

public interface UserStorage {
    User addUsers(User user);

    User updateUser(User user);

    void deliteUserById(int userId);

    User getUserById(int userId);

    Collection<User> getUsers();

    Collection<Integer> getUsersIds();

    User updateUsersFriend(int userId, int usersFriendId);

    User deleteUsersFriend(int userId, int usersFriendId);

}
