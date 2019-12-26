package com.shana.cinema.service;

import com.shana.cinema.mapper.ScreenFilmMapper;
import com.shana.cinema.pojo.ScreenFilm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2019/10/22
 * @since 1.0.0
 */
@Service
public class HScreenFilmServiceImpl implements HIScreenFilmService {
    @Autowired
    private ScreenFilmMapper screenFilmMapper;

    @Override
    public List<ScreenFilm> findScreenFilmByScreenAndTime(ScreenFilm sf) {
        return screenFilmMapper.selectScreenFilmByScreenAndTime(sf);
    }

    @Override
    public int addScreenFilm(ScreenFilm sf) {
        return screenFilmMapper.insert(sf);
    }

    @Override
    public ScreenFilm findBySfid(int sfid) {
        return screenFilmMapper.selectBySfid(sfid);
    }

    @Override
    public List<ScreenFilm> findBySid(int sid) {
        return screenFilmMapper.selectBySid(sid);
    }
}
