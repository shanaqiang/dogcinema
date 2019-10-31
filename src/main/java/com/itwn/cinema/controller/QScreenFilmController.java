package com.itwn.cinema.controller;

import com.itwn.cinema.response.FilmScreen01;
import com.itwn.cinema.response.ResponseResult;
import com.itwn.cinema.service.QIScreenFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2019/10/24
 * @since 1.0.0
 */
@Controller
@RequestMapping("/qschedule")
public class QScreenFilmController {
    @Autowired
    private QIScreenFilmService screenFilmService;

    @RequestMapping("/showonefilmschedule")
    @ResponseBody
    public ResponseResult showOne(int fid, int cbid){
        ResponseResult rs = new ResponseResult();
        List<FilmScreen01> lists = screenFilmService.findFilmScreenByFidAndSid(fid, cbid);
        //System.out.println(lists);
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
        sdf1.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Set<String> set = new LinkedHashSet<>();
        //当前时间之后的场次
        List<FilmScreen01> list = new ArrayList<>();
        for(FilmScreen01 f : lists){
            Date date = new Date(f.getShowtime().getTime()+f.getScheduledate().getTime()+8*3600000);
            Date date1 = new Date(f.getScheduledate().getTime()+24*3600000);
            f.setShowtime(date);
            f.setShow(sdf1.format(f.getShowtime()));
            f.setEnd(sdf1.format(f.getEndtime()));
            //System.out.println(f.getScheduledate());
            if(date1.after(new Date())){
                set.add(sdf.format(f.getScheduledate()));
            }

            if(date.after(new Date())){
                list.add(f);
            }
            //System.out.println(sdf.format(f.getScheduledate()));
        }
        List<String> list2 = new ArrayList<>(set);
        List<List<FilmScreen01>> lists1 = new ArrayList<>();
        for(String d : set){
            List<FilmScreen01> list1 = new ArrayList<>();
            for(FilmScreen01 f : list){
                if(sdf.format(f.getScheduledate()).equals(d)){
                    list1.add(f);
                    //System.out.println(f.getShowtime());
                }
            }
            if(list1.size()!=0) {
                lists1.add(list1);
            }
        }
        rs.add("date",list2).add("schedule",lists1);
        return rs;
    }

    @RequestMapping("/showallfilm")
    @ResponseBody
    public List<FilmScreen01> showAllByCbid(int cbid){
        List<FilmScreen01> list = screenFilmService.findAllByCbid(cbid);
        List<FilmScreen01> list1 = new ArrayList<>();
        for(FilmScreen01 f : list){
            Date date = new Date(f.getScheduledate().getTime()+24*3600000);
            if(date.after(new Date())){
                if(list1.size()==0){
                    list1.add(f);
                }else {
                    int count = 0;
                    for(FilmScreen01 f1 : list1){
                        if(f.getFname().equals(f1.getFname())){
                            count++;
                        }
                    }
                    if(count==0){
                        list1.add(f);
                    }
                }
            }
        }

        return list1;
    }
}
