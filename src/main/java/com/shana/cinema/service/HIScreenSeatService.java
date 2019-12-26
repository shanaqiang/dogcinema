package com.shana.cinema.service;

import com.shana.cinema.pojo.ScreenSeats;

import java.util.List;

public interface HIScreenSeatService {

    List<ScreenSeats> findBySfid(int sfid);
}
