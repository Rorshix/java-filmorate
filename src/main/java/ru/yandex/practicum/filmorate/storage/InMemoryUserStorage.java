package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

import java.util.*;

@Component
public class InMemoryUserStorage implements UserStorage {
    private int generator = 0;
    private final Map<Integer, User> allUsers = new HashMap<>();

    @Override
    public User get(int userId) {
        return allUsers.get(userId);
    }

    @Override
    public List<User> getAllUsersList() {
        return new ArrayList<>(allUsers.values());
    }

    @Override
    public User save(User user) {
        if (!allUsers.containsKey(user.getId())) {
            user.setId(++generator);
        }
        allUsers.put(user.getId(), user);
        return user;
    }
}