package com.itwn.cinema.service;

import com.itwn.cinema.mapper.FilmMapper;
import com.itwn.cinema.pojo.Film;
import com.itwn.cinema.pojo.Qv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author asus
 * @create 2019/10/20
 * @since 1.0.0
 */
@Service
public class HfilmServiceImp implements HfilmService{
    @Autowired
    FilmMapper filmMapper;
    @Override
    public void addfilm(Film film) {
        filmMapper.insert(film);
    }

    @Override
    public List<Film> findFilm(Qv qv) {
        List<Film> list=filmMapper.selectFilmByFnameAndReleasedateAndStatusAndrecommend(qv);
        return list;
    }

    @Override
    public Film findFilmByFid(int fid) {
        Film film=filmMapper.selectFilmByFid(fid);
        return film;
    }

    @Override
    public void changefilm(Film film) {
        System.out.println(film);
        filmMapper.updateByPrimaryKey(film);
    }

    @Override
    public List<Film> findFilmByRelease(String date) {
        return filmMapper.selectFilmByRelease(date);
    }

    @Override
    public int findAllCount() {
        int totalcount=filmMapper.selectcount();
        return totalcount;
    }
}
