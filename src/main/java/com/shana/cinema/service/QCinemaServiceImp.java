package com.shana.cinema.service;

import com.shana.cinema.mapper.CinemaBranchMapper;
import com.shana.cinema.pojo.CinemaBranch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author xiana
 * @create 2019/10/23
 * @since 1.0.0
 */
@Service
@Transactional
public class QCinemaServiceImp implements QICinemaService{

    @Autowired
    CinemaBranchMapper cinemaBranchMapper;

    @Override
    public List<CinemaBranch> findAll() {
        return cinemaBranchMapper.selectAll();
    }

    @Override
    public CinemaBranch findByCid(int cid) {
        return cinemaBranchMapper.selectByPrimaryKey(cid);
    }
}

