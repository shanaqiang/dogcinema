package com.itwn.cinema.utils;

import com.itwn.cinema.pojo.ScreenChair;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author xiana
 * @create 2019/10/21
 * @since 1.0.0
 */
public class GetSeats {

    public static List<ScreenChair> setSeats(String str,int sid,int status,List<ScreenChair> screenChairs){
        while (str.contains(",")){
            int x=Integer.parseInt(str.substring(str.indexOf("(")+1,str.indexOf(",")));
            int y=Integer.parseInt(str.substring(str.indexOf(",")+1,str.indexOf(")")));
            ScreenChair screenChair=new ScreenChair();
            screenChair.setSid(sid);
            screenChair.setX(x);
            screenChair.setY(y);
            screenChair.setStatus(status);
            str=str.substring(str.indexOf(")")+1,str.length());
            screenChairs.add(screenChair);
        }
        return screenChairs;
    }
}

