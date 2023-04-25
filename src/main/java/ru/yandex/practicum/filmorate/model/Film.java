package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.yandex.practicum.filmorate.validator.FilmValidator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Builder
public class Film {
    private final Set<Integer> userLikes = new LinkedHashSet<>();
    private Integer id;
    @EqualsAndHashCode.Exclude
    @NotBlank(message = "Не должно быть пустым")
    private String name;
    @EqualsAndHashCode.Exclude
    @Size(max = 200, message = "Количество символов должно быть менее 200")
    private String description;
    @EqualsAndHashCode.Exclude
    @FilmValidator
    private LocalDate releaseDate;
    @EqualsAndHashCode.Exclude
    @Positive(message = "Должно быть больше нуля")
    private Integer duration;

    public void setId(Integer generateId) {
    }
}
