package com.itwn.cinema.service;

import com.itwn.cinema.pojo.Film;
import com.itwn.cinema.response.FilmLimit3;

import java.util.List;

public interface QIFilmService {


    List<Film> showAll();

    Film findFilmByFid(int fid);

    List<Film> findByLimit3(FilmLimit3 filmLimit3);

    List<Film> findFilmsByFname(String fname);

    List<Film> findFilmsByType(String type);
}
