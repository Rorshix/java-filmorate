package ru.yandex.practicum.filmorate.service;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.List;

@Service
public class InMemoryUserService implements UserService {
	private final UserStorage userStorage;

	public InMemoryUserService(UserStorage userStorage) {
		this.userStorage = userStorage;
	}

	@Override
	public List<User> getAll() {
		return userStorage.getListUsers();
	}

	@Override
	public User getUser(Integer id) {
		User user = userStorage.getUser(id);

		if (user == null) {
			throw new UserNotFoundException("Такого пользователя нет");
		}

		return user;
	}

	@Override
	public User addUser(User newUser) {
		checkUserName(newUser);
		return userStorage.addUser(newUser);
	}

	@Override
	public User updateUser(User user) {
		if (userStorage.getUser(user.getId()) == null) {
			throw new UserNotFoundException("Такого пользователя нет");
		}
		checkUserName(user);
		return userStorage.updateUser(user.getId(), user);
	}

	@Override
	public User delUser(Integer id) {
		User user = userStorage.delUser(id);
		if (user == null) {
			throw new UserNotFoundException("Такого пользователя нет");
		}
		return user;
	}

	private void checkUserName(User user) {
		if (user.getName() == null || user.getName().isBlank()) {
			user.setName(user.getLogin());
		}
	}
}