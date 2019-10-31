package com.itwn.cinema.service;

import com.itwn.cinema.mapper.FilmMapper;
import com.itwn.cinema.pojo.Film;
import com.itwn.cinema.response.FilmLimit3;
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
public class QFilmServiceImp implements QIFilmService{

    @Autowired
    FilmMapper filmMapper;

    @Override
    public List<Film> showAll() {
        return filmMapper.selectAll();
    }

    @Override
    public Film findFilmByFid(int fid) {
        return filmMapper.selectByPrimaryKey(fid);
    }

    @Override
    public List<Film> findByLimit3(FilmLimit3 filmLimit3) {

        return filmMapper.selectByFilmLimit3(filmLimit3);
    }

    @Override
    public List<Film> findFilmsByFname(String fname) {
        return filmMapper.selectByFname(fname);
    }

    @Override
    public List<Film> findFilmsByType(String type) {
        return filmMapper.selectByType(type);
    }
}

