package com.shana.cinema.service;

import com.shana.cinema.pojo.Film;
import com.shana.cinema.pojo.Qv;

import java.util.List;

public interface HfilmService {
    void addfilm(Film film);

    List<Film> findFilm(Qv qv);
    Film findFilmByFid(int fid);
    void changefilm(Film film);

    List<Film> findFilmByRelease(String date);

    int findAllCount();
}
