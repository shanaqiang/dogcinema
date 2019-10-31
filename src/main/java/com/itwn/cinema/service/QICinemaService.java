package com.itwn.cinema.service;

import com.itwn.cinema.pojo.CinemaBranch;

import java.util.List;

public interface QICinemaService {

    List<CinemaBranch> findAll();

    CinemaBranch findByCid(int cid);
}
