package com.itwn.cinema.service;

import com.itwn.cinema.mapper.FilmMapper;
import com.itwn.cinema.pojo.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author asus
 * @create 2019/10/21
 * @since 1.0.0
 */
@Service
public class FilmShowServiceImp implements FilmShowService{
    @Autowired
    FilmMapper filmMapper;

    @Override
    public List<Film> findhotfilm() {
        List<Film> list=filmMapper.selectHotFilmByStatusAndRecommend();
        return list;
    }

    @Override
    public List<Film> findcommingfilm() {
        List<Film> list=filmMapper.selectCommingFilmByStatusAndRecommend();
        return list;
    }

    @Override
    public List<Film> orderfilmbyboxoffice() {
        List<Film> list=filmMapper.selectFilmOrderByBoxoffice();
        return list;
    }

    @Override
    public List<Film> orderfilmbyscore() {
        List<Film> list=filmMapper.selectFilmOrderByScore();
        return list;
    }

    @Override
    public List<Film> orderfilmbygradecount() {
        List<Film> list=filmMapper.selectFilmOrderByGradecount();
        return list;
    }
}
