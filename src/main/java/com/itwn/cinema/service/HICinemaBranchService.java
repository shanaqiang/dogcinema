package com.itwn.cinema.service;


import com.itwn.cinema.pojo.CinemaBranch;

import java.util.List;

public interface HICinemaBranchService {

    List<CinemaBranch> findAll();

    void delecteByCdid(Integer cdid);

    CinemaBranch findByCbid(int cbid);

    void insertbrand(CinemaBranch cinemaBranch);

    void updataBrand(CinemaBranch cinemaBranch);

    CinemaBranch selectByPrimaryKey(Integer cdid);
}
