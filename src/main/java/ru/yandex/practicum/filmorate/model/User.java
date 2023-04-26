package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;
    private String name;
    @Past
    private LocalDate birthday;
    @NotEmpty
    @NotBlank
    @Email
    private String email;
    private String login;

    @JsonIgnore
    Set<Integer> friendsId = new HashSet<>();

    public User(Integer id, String name, LocalDate birthday, String email, String login) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.login = login;
    }
}