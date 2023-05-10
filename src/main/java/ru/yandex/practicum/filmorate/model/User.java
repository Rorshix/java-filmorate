package ru.yandex.practicum.filmorate.model;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
public class User {

    private int id;
    @Email
    private String email;
    @NotBlank
    @NotNull
    private String login;
    private String name;
    @PastOrPresent
    private LocalDate birthday;
    private Set<Integer> friends;


    public Set<Integer> getFriends() {
        return friends;
    }

    public User deleteFriends(int userId) {
        friends.remove(userId);
        return this;
    }

    public void addToFriens(int userId) {
        friends.add(userId);
    }
}