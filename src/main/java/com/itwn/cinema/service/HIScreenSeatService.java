package com.itwn.cinema.service;

import com.itwn.cinema.pojo.ScreenSeats;

import java.util.List;

public interface HIScreenSeatService {

    List<ScreenSeats> findBySfid(int sfid);
}
