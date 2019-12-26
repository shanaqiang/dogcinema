package com.shana.cinema.service;

import com.shana.cinema.pojo.ScreenChair;
import com.shana.cinema.pojo.ScreenFilm;
import com.shana.cinema.pojo.ScreenSeats;
import com.shana.cinema.pojo.Screens;


import java.util.List;

public interface HIScreenService {

    void addScreen(Screens screens);

    Screens findScreenBySname(String sname);

    List<Screens> findScreensByCbid(int cbid);

    Screens findScreenBySid(int sid);

    void addMuchChairs(List<ScreenChair> screenChairs);

    List<ScreenChair> findScreenChairBySid(int sid);


    List<Screens> findAll();

    void changeScreeenStatusBySid(int sid,int status);

    ScreenFilm findScreenFilmBySfid(int sfid);

    void AddScreenSeats(ScreenSeats screenSeats);

    List<Screens> findByCbidAndStatus(int cbid);

    List<ScreenFilm> findScreenFilmByFid(int fid);
}
