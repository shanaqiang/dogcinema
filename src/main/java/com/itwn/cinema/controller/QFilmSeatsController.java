package com.itwn.cinema.controller;

import com.itwn.cinema.pojo.*;
import com.itwn.cinema.response.ResponseResult;
import com.itwn.cinema.response.SeatYa;
import com.itwn.cinema.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author xiana
 * @create 2019/10/24
 * @since 1.0.0
 */
@Controller
@RequestMapping("/fseats")
public class QFilmSeatsController {

    @Autowired
    HIScreenSeatService screenSeatService;
    @Autowired
    HIScreenFilmService screenFilmService;
    @Autowired
    HfilmService filmService;
    @Autowired
    HIScreenService screenService;
    @Autowired
    HICinemaBranchService cinemaBranchService;

    @RequestMapping("/gofseats/{sfid}")
    public String goFseats(@PathVariable(value = "sfid")int sfid, HttpServletRequest request){
        request.setAttribute("sfid",sfid);
        return  "front/chooseseat";
    }

    //电影选座界面的相关初始数据
    @RequestMapping("/showfseats")
    @ResponseBody
    public ResponseResult showFSeats(int sfid){
        List<ScreenSeats> screenSeats = screenSeatService.findBySfid(sfid);
        //判断座位是否过期

        for(ScreenSeats ss:screenSeats){
             if(ss.getStatus()==3){
                 if(ss.getNotpaydate()!=null){
                     Date date=new Date();
                     Date date1=ss.getNotpaydate();
                     if((date.getTime()-date1.getTime())>=(1000*60*10)){
                         ss.setStatus(1);
                     }else{
                         ss.setStatus(2);
                     }
                 }else{
                     ss.setStatus(2);
                 }
             }
        }
        //排序
        int maxX=0;
        int maxY=0;

        for(ScreenSeats ss:screenSeats){
            if(ss.getX()>maxX){
                maxX=ss.getX();
            }
            if(ss.getY()>maxY){
                maxY=ss.getY();
            }
        }
        //把y坐标相同的放一个List中
        List<SeatYa> lists=new ArrayList<>();
        for(int i=1;i<=maxY;i++){
            SeatYa seatY=new SeatYa();
            for(int j=0;j<screenSeats.size();j++){
                if(screenSeats.get(j).getY()==i){
                    seatY.setY(i);
                    seatY.add(screenSeats.get(j));
                }
            }
            lists.add(seatY);
        }
        //查询sfid对应的影片
        ScreenFilm screenFilm=screenFilmService.findBySfid(sfid);
        Film film=filmService.findFilmByFid(screenFilm.getFid());
        //查询影厅
        Screens screen=screenService.findScreenBySid(screenFilm.getSid());
        //查询影院
        CinemaBranch cinemaBranch=cinemaBranchService.findByCbid(screen.getCbid());
        ResponseResult rs=new ResponseResult();
        rs.add("seats",lists);
        rs.add("film",film);
        rs.add("sf",screenFilm);
        rs.add("screen",screen);
        rs.add("cinemabranch",cinemaBranch);
        return rs;
    }

    @RequestMapping("/test")
    @ResponseBody
    public int test(String[] ssida){
        for(String str:ssida){
            System.out.println(str);
        }

        return 1;
    }
}

