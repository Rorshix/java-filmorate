package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;
import java.util.Collection;

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
}