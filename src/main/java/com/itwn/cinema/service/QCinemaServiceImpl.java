package com.itwn.cinema.service;

import com.itwn.cinema.mapper.CinemaBranchMapper;
import com.itwn.cinema.pojo.CinemaBranch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2019/10/24
 * @since 1.0.0
 */
@Service
@Transactional
public class QCinemaServiceImpl implements QICinemaBranchService {
    @Autowired
    private CinemaBranchMapper branchMapper;


    @Override
    public CinemaBranch findBranchByCdid(int id) {
        return branchMapper.selectByPrimaryKey(id);
    }
}
