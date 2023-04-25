package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserStorage userStorage;

    public List<User> getAllUsers() {
        return userStorage.getAllUsers();
    }

    public User getUserById(Integer id) {
        User user = userStorage.getUserById(id);
        if (user == null) {
            throw new NotFoundException("Пользователь не найден");
        }
        return user;
    }

    public User putUser(User user) {
        if (userStorage.getAllUsers().contains(user)) {
            throw new ValidationException("Пользователь уже присутствует в базе данных");
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
        return userStorage.putUser(user);
    }

    public User updateUser(User user) {
        if (user.getId() == null || !userStorage.getAllUsers().contains(user)) {
            throw new NotFoundException("Пользователь не найден");
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
        return userStorage.updateUser(user);
    }

    public List<User> findAllFriends(Integer userId) {
        checkAndReturnUser(userId);
        return userStorage.getUserById(userId).getFriendsList().stream()
                .map(userStorage::getUserById)
                .collect(Collectors.toList());
    }

    public void addToFriendsList(Integer userId, Integer friendId) {
        User user = checkAndReturnUser(userId);
        User friend = checkAndReturnUser(friendId);
        user.getFriendsList().add(friendId);
        friend.getFriendsList().add(userId);
    }

    public void removeFromFriendsList(Integer userId, Integer friendId) {
        User user = checkAndReturnUser(userId);
        User friend = checkAndReturnUser(friendId);
        user.getFriendsList().remove(friendId);
        friend.getFriendsList().remove(userId);
    }

    public List<User> findCommonFriends(Integer userId, Integer friendId) {
        checkAndReturnUser(userId);
        checkAndReturnUser(friendId);
        Set<Integer> userFriendList = userStorage.getUserById(userId).getFriendsList();
        return userStorage.getUserById(friendId).getFriendsList().stream()
                .filter(userFriendList::contains)
                .map(userStorage::getUserById)
                .collect(Collectors.toList());
    }

    private User checkAndReturnUser(Integer userId) {
        User user = userStorage.getUserById(userId);
        if (userId == null || user == null) {
            throw new NotFoundException("Пользователь не найден");
        }
        return user;
    }
}