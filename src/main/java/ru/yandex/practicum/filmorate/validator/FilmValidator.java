package ru.yandex.practicum.filmorate.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = UserValidator.class)
@Documented
public @interface FilmValidator {

    String message() default "Год выпуска фильма не ранее 28.12.1895 и не позднее настоящего времени";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

