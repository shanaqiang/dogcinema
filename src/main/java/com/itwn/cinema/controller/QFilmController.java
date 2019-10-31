package com.itwn.cinema.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itwn.cinema.pojo.Film;
import com.itwn.cinema.response.FilmLimit3;
import com.itwn.cinema.service.QIFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author xiana
 * @create 2019/10/23
 * @since 1.0.0
 */
@Controller
@RequestMapping("/qfilm")
public class QFilmController {

    @Autowired
    QIFilmService filmService;

    @RequestMapping("/showallfilmsinshow")
    @ResponseBody
    public PageInfo showAllFilms(@RequestParam(value = "pagenum",defaultValue = "1") int pagenum,
                                   @RequestParam(value = "pagesize",defaultValue = "12") int pagesize,
                                 String year,String country,String type){
        if(year!=null && year.equals("全部")){
            year=null;
        }
        if(country!=null && country.equals("全部")){
            country=null;
        }
        if(type!=null && type.equals("全部")){
            type=null;
        }
        FilmLimit3 filmLimit3=new FilmLimit3();
        filmLimit3.setYear(year);
        filmLimit3.setCountry(country);
        filmLimit3.setType(type);
        PageHelper.startPage(pagenum,pagesize);
        List<Film> list=filmService.findByLimit3(filmLimit3);
        PageInfo info=new PageInfo(list);
        return info;
    }

    @RequestMapping("/showonefilm")
    @ResponseBody
    public Film showOneFilm(int fid){

        return filmService.findFilmByFid(fid);
    }

    @RequestMapping("/showonebyname")
    public String showOneFilmBuFname(String fname, Map map){
        List<Film> films= filmService.findFilmsByFname(fname);
        List<Film> films1=filmService.findFilmsByType(fname);
        for(Film f:films1){
            films.add(f);
        }
        map.put("films",films);
        return "front/filmssearched";
    }

}

