package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.FilmsAndUsersValidationException;
import ru.yandex.practicum.filmorate.exception.MissingException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserStorage userStorage;

    public User getCustomersDyId(Integer id) {
        return userStorage.getUserById(id);
    }

    public Collection<User> getCustomers() {
        return userStorage.getUsers();
    }

    public User addUsers(User user) {
        if (user.getName() == null || user.getName().equals("")) {
            user.setName(user.getLogin());
        }
        return userStorage.addUsers(user);
    }

    public User updateUser(User user) {
        return userStorage.updateUser(user);
    }

    public void deliteUserById(Integer id) {
        userStorage.deliteUserById(id);
    }

    public User addToFriends(int userId, int friendId) {
        if (!userStorage.getUsersIds().contains(userId) || !userStorage.getUsersIds().contains(friendId)) {
            throw new MissingException("Введен несуществующий id");
        }
        return userStorage.updateUsersFriend(userId, friendId);
    }

    public User deliteFromFriends(int userId, int friendId) {
        return userStorage.deleteUsersFriend(userId, friendId);
    }

    public Collection<User> takeFriendsList(int userId) {
        Collection<User> friends = new ArrayList<>();
        if (userStorage.getUserById(userId).getFriends().isEmpty()) {
            return friends;
        }
        Set<Integer> frendsIds = userStorage.getUserById(userId).getFriends();
        frendsIds.forEach(id -> friends.add(userStorage.getUserById(id)));
        return friends;
    }

    public Collection<User> takeCommonFriendsList(int userId, int otherUserId) {
        Collection<User> commonFriends = new ArrayList<>();
        if (userStorage.getUserById(userId).getFriends().isEmpty() || userStorage.getUserById(userId).getFriends() == null) {
            return commonFriends;
        }
        Set<Integer> frendsIds = userStorage.getUserById(userId).getFriends();
        Set<Integer> otherFrendsIds = userStorage.getUserById(otherUserId).getFriends();
        frendsIds.stream()
                .filter(otherFrendsIds::contains)
                .collect(Collectors.toList())
                .forEach(id -> commonFriends.add(userStorage.getUserById(id)));
        return commonFriends;
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