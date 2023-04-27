package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.util.*;

@Slf4j
@Validated
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        log.info("Получен запрос. Список всех пользователей");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        log.info("Получен запрос. Список всех пользователей");
        return userService.get(id);
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        userService.save(user);
        log.info("Добавлен пользователь: " +
                user.getName() + " ID: " + user.getId() + " эмэйл: " +
                user.getEmail() + " логин: " + user.getLogin() +
                " дата рождения: " + user.getBirthday());
        return user;
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user) {
        userService.get(user.getId());
        userService.save(user);
        log.info("Обновлён пользователь: " +
                user.getName() + " ID: " + user.getId() + " эмэйл: " +
                user.getEmail() + " логин: " + user.getLogin() +
                " дата рождения: " + user.getBirthday());
        return user;
    }
}
