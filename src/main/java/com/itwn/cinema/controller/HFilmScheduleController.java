package com.itwn.cinema.controller;

import com.itwn.cinema.pojo.ScreenChair;
import com.itwn.cinema.pojo.ScreenFilm;
import com.itwn.cinema.pojo.ScreenSeats;
import com.itwn.cinema.service.HIScreenFilmService;
import com.itwn.cinema.service.HIScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2019/10/22
 * @since 1.0.0
 */
@Controller
@RequestMapping("/schedule")
public class HFilmScheduleController {
    @Autowired
    private HIScreenFilmService screenFilmService;
    @Autowired
    HIScreenService screenService;


    //增加拍片
    @RequestMapping("/addschedule")
    @ResponseBody
    @Transactional
    public String addschedule(ScreenFilm sf){
        //System.out.println(sf);

        screenFilmService.addScreenFilm(sf);
        //排片id   sf.getSfid();
        //根据返回的排片id查询影厅id
        ScreenFilm screenFilm = screenService.findScreenFilmBySfid(sf.getSfid());
        //根据影厅id查询对应的影厅座位
        List<ScreenChair> screenChairs = screenService.findScreenChairBySid(screenFilm.getSid());
        //遍历该影厅的座位
        for(ScreenChair sc:screenChairs){
            //根据影厅座位将场次对应的影厅座位（chair）放到数据库排片、影厅、座位（seats）中
            ScreenSeats screenSeats=new ScreenSeats();
            screenSeats.setSfid(sf.getSfid());
            screenSeats.setX(sc.getX());
            screenSeats.setY(sc.getY());
            screenSeats.setNowprice(screenFilm.getPrice());
            screenSeats.setDescription(screenFilm.getDescription());
            screenSeats.setStatus(sc.getStatus());
            //将填充好的排片座位插入数据库中
            screenService.AddScreenSeats(screenSeats);
        }
        return "添加成功";
    }

}
