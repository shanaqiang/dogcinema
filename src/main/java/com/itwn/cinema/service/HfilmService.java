package com.itwn.cinema.service;

import com.itwn.cinema.pojo.Film;
import com.itwn.cinema.pojo.Qv;

import java.util.List;

public interface HfilmService {
    void addfilm(Film film);

    List<Film> findFilm(Qv qv);
    Film findFilmByFid(int fid);
    void changefilm(Film film);

    List<Film> findFilmByRelease(String date);

    int findAllCount();
}
