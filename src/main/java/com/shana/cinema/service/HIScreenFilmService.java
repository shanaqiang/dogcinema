package com.shana.cinema.service;

import com.shana.cinema.pojo.ScreenFilm;

import java.util.List;

public interface HIScreenFilmService {
    List<ScreenFilm> findScreenFilmByScreenAndTime(ScreenFilm sf);

    int addScreenFilm(ScreenFilm sf);

    ScreenFilm findBySfid(int sfid);

    List<ScreenFilm> findBySid(int sid);


}
