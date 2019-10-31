package com.itwn.cinema.controller;

import com.itwn.cinema.pojo.Film;
import com.itwn.cinema.service.FilmShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author asus
 * @create 2019/10/21
 * @since 1.0.0
 */
@Controller
@RequestMapping("film")
public class FilmShowController {
    @Autowired
    FilmShowService filmShowService;

    //显示首页正在上映电影信息
    @RequestMapping("showhotfilm")
    @ResponseBody
    public List<Film> showhotfilm(){
        List<Film> list=filmShowService.findhotfilm();
        return list;
    }

    //显示首页即将上映电影
    @RequestMapping("showcommingfilm")
    @ResponseBody
    public List<Film> showcommingfilm(){
        List<Film> list=filmShowService.findcommingfilm();
        return list;
    }

    //票行排行首位
    @RequestMapping("showordertopfilm")
    @ResponseBody
    public List<Film> showordertopfilm(){
        List<Film> list=filmShowService.orderfilmbyboxoffice();
        return list;
    }

    //票行排行首位
    @RequestMapping("showorderfilm")
    @ResponseBody
    public List<Film> showorderfilm(){
        List<Film> list=filmShowService.orderfilmbyboxoffice();
        list.remove(0);
        return list;
    }

    //评分首位
    @RequestMapping("showtopbyscore")
    @ResponseBody
    public List<Film> showtopbyscore(){
        List<Film> list=filmShowService.orderfilmbyscore();
        return list;
    }

    //评分排行
    @RequestMapping("showorderbyscore")
    @ResponseBody
    public List<Film> showorderbyscore(){
        List<Film> list=filmShowService.orderfilmbyscore();
        list.remove(0);
        return list;
    }

    //预播评论数前三排行
    @RequestMapping("showtopthreebygradecount")
    @ResponseBody
    public List<Film> showtopthreebygradecount(){
        List<Film> list=filmShowService.orderfilmbygradecount();
        return list;
    }


    //预播评论数排行
    @RequestMapping("showorderbyshoworderbygradecount")
    @ResponseBody
    public List<Film> showorderbyshoworderbygradecount(){
        List<Film> list=filmShowService.orderfilmbygradecount();
        list.remove(0);
        list.remove(1);
        list.remove(2);
        return list;
    }
}
