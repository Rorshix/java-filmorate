package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryUserStorage implements UserStorage {

	private final Map<Integer, User> users = new HashMap<>();

	private Integer globalId;

	@Override
	public List<User> getAllUsers() {
		return List.copyOf(users.values());
	}

	@Override
	public void removeAllUsers() {
		users.clear();
	}

	@Override
	public User getUserById(Integer id) {
		return users.get(id);
	}

	@Override
	public User putUser(User user) {
		user.setId(generateId());
		users.put(user.getId(), user);
		return user;
	}

	@Override
	public User updateUser(User user) {
		users.put(user.getId(), user);
		return user;
	}

	@Override
	public void removeUserById(Integer id) {
		users.remove(id);
	}

	private Integer generateId() {
		return globalId++;
	}
}