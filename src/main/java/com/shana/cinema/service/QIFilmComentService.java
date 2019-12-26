package com.shana.cinema.service;

import com.shana.cinema.pojo.FilmComent;

import java.util.List;

public interface QIFilmComentService {
    List<FilmComent> findAllComent();

    void addComent(FilmComent coment);

    List<FilmComent> findComentByFilmId(int fid);
}
