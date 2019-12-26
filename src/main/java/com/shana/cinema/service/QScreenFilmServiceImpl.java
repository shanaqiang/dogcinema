package com.shana.cinema.service;

import com.shana.cinema.mapper.ScreenFilmMapper;
import com.shana.cinema.response.FilmScreen01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
public class QScreenFilmServiceImpl implements QIScreenFilmService {
    @Autowired
    private ScreenFilmMapper screenFilmMapper;
    @Override
    public List<FilmScreen01> findFilmScreenByFidAndSid(int fid, int cbid) {
        return screenFilmMapper.selectScreenFilmByFidAndSid(fid,cbid);
    }

    @Override
    public List<FilmScreen01> findAllByCbid(int cbid) {
        return screenFilmMapper.selectScreenFilmByCbid(cbid);
    }
}
