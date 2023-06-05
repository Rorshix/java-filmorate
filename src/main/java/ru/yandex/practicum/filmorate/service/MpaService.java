package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dao.MpaDaoImpl;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MpaService {
    private final MpaDaoImpl mpaDao;

    public List<Mpa> getMpa() {
        return mpaDao.getMpa();
    }

    public Mpa getMpasById(int id) {
        return mpaDao.getMpaById(id);
    }
}
