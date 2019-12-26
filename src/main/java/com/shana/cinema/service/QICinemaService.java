package com.shana.cinema.service;

import com.shana.cinema.pojo.CinemaBranch;

import java.util.List;

public interface QICinemaService {

    List<CinemaBranch> findAll();

    CinemaBranch findByCid(int cid);
}
