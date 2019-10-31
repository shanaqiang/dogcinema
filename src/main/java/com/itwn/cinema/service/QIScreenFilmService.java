package com.itwn.cinema.service;

import com.itwn.cinema.response.FilmScreen01;

import java.util.List;

public interface QIScreenFilmService {
    List<FilmScreen01> findFilmScreenByFidAndSid(int fid,int cbid);

    List<FilmScreen01> findAllByCbid(int cbid);
}
