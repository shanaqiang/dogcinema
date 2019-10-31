package com.itwn.cinema.service;

import com.itwn.cinema.mapper.ScreenSeatsMapper;
import com.itwn.cinema.pojo.ScreenSeats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author xiana
 * @create 2019/10/24
 * @since 1.0.0
 */
@Service
@Transactional
public class HScreenSeatServiceImp  implements HIScreenSeatService {

    @Autowired
    ScreenSeatsMapper screenSeatsMapper;

    @Override
    public List<ScreenSeats> findBySfid(int sfid) {
        return screenSeatsMapper.selectBySfid(sfid);
    }
}

