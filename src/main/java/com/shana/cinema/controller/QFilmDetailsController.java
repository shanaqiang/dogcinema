package com.shana.cinema.controller;

import com.shana.cinema.mapper.FilmMapper;
import com.shana.cinema.pojo.Film;
import com.shana.cinema.service.QIFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2019/10/23
 * @since 1.0.0
 */
@Controller
@RequestMapping("/qfilmdetails")
public class QFilmDetailsController {
    @Autowired
    private QIFilmService filmService;
    @Autowired
    private FilmMapper filmMapper;

    @RequestMapping("/details/{id}")
    public String filmdetails(@PathVariable(value = "id") int fid, Map map){
        //System.out.println(fid);
        Film film = filmMapper.selectFilmByFid(fid);
        //System.out.println(film.getFilmimg());
        map.put("film",film);
        return "front/filmdetails.html";
    }

}
