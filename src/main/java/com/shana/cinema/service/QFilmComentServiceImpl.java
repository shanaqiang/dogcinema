package com.shana.cinema.service;

import com.shana.cinema.mapper.FilmComentMapper;
import com.shana.cinema.pojo.FilmComent;
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
public class QFilmComentServiceImpl implements QIFilmComentService {

    @Autowired
    FilmComentMapper filmComentMapper;

    @Override
    public List<FilmComent> findAllComent() {
        return filmComentMapper.selectAll();
    }

    @Override
    public void addComent(FilmComent coment) {
        filmComentMapper.insert(coment);
    }

    @Override
    public List<FilmComent> findComentByFilmId(int fid) {
        return filmComentMapper.selectByFilmId(fid);
    }
}
