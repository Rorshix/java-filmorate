package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public Collection<User> findAll() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User create(@RequestBody @Valid User user) {
        User createdUser = userService.putUser(user);
        log.debug("Пользователь добавлен");
        return createdUser;
    }

    @PutMapping
    public User put(@Valid @RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        log.debug("Пользователь обновлен");
        return updatedUser;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(name = "id") Integer userId) {
        return userService.getUserById(userId);
    }
}