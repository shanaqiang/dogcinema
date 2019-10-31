package com.itwn.cinema.service;

import com.itwn.cinema.pojo.Film;

import java.util.List;

public interface FilmShowService {
    //热播
    List<Film> findhotfilm();
    //即将上映
    List<Film> findcommingfilm();
    //票行排行
    List<Film> orderfilmbyboxoffice();
    //评分排行
    List<Film> orderfilmbyscore();
    //预播评论数排行
    List<Film> orderfilmbygradecount();
}
