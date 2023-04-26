package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.MissingException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public Collection<User> getCustomers() { // Получение всех юзеров
        return userService.getCustomers();
    }

    @GetMapping("/{id}")
    public User getCustomersDyId(@PathVariable("id") Integer id) throws MissingException { // Получение юзера по id
        return userService.getCustomersDyId(id);
    }

    @PostMapping()
    public User addUsers(@Valid @RequestBody User user) throws ValidationException {
        return userService.addUsers(user);
    }

    @PutMapping()
    public User updateUser(@Valid @RequestBody User user) throws ValidationException, MissingException {
        return userService.updateUser(user);
    }

    @PutMapping("/{id}")
    public void deliteUserById(@PathVariable("id") Integer id) throws ValidationException {
        userService.deliteUserById(id);
    }
}