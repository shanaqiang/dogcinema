package com.shana.cinema.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shana.cinema.pojo.CinemaBranch;
import com.shana.cinema.pojo.ScreenChair;
import com.shana.cinema.pojo.Screens;
import com.shana.cinema.response.ResponseResult;
import com.shana.cinema.response.ScreenQv;
import com.shana.cinema.response.SeatY;
import com.shana.cinema.service.HICinemaBranchService;
import com.shana.cinema.service.HIScreenService;
import com.shana.cinema.utils.GetSeats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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
@RestController
@RequestMapping("/screen")
public class HScreenController {


    @Autowired
    HIScreenService hScreenService;
    @Autowired
    HICinemaBranchService cinemaBranchService;



    @RequestMapping("/addscreen")
    @ResponseBody
    public int addScreen(Screens screens){

        System.out.println(screens);
        hScreenService.addScreen(screens);
        return 1;
    }

    @RequestMapping("/show")
    @ResponseBody
    public List<CinemaBranch> show(){
        List<CinemaBranch> all = cinemaBranchService.findAll();
        return all;
    }

    @RequestMapping("/showscreens")
    @ResponseBody
    public List<Screens> addshow(int cbid){
        List<Screens> screensByCbid = hScreenService.findByCbidAndStatus(cbid);
        return screensByCbid;
    }

    @RequestMapping("/showallscreens")
    @ResponseBody
    public ResponseResult showAllScreens(@RequestParam(value = "pagenum",defaultValue = "1") int pagenum,
                                        @RequestParam(value = "pagesize",defaultValue = "3") int pagesize){
        PageHelper.startPage(pagenum,pagesize);
        List<Screens> screens= hScreenService.findAll();
        PageInfo<Screens> pageInfo=new PageInfo(screens);
        //查询影厅对应的影院
        List<ScreenQv> screenQvs=new ArrayList<>();
        for (Screens s:screens){
            CinemaBranch cb = cinemaBranchService.findByCbid(s.getCbid());
            ScreenQv screenQv=new ScreenQv();
            screenQv.setScreens(s);
            screenQv.setCinemaBranch(cb);
            screenQvs.add(screenQv);
        }

        ResponseResult responseResult=new ResponseResult();
        responseResult.add("pageinfo",pageInfo);
        responseResult.add("screenQv",screenQvs);
        return responseResult;
    }


    @RequestMapping("/addchairs")
    public void addChairs(String havechair,String nothave,int sid){

        List<ScreenChair> screenChairs=new ArrayList<>();
        GetSeats.setSeats(havechair,sid,1,screenChairs);
        GetSeats.setSeats(nothave,sid,0,screenChairs);
        //按y轴排序
        for(int i=0;i<screenChairs.size()-1;i++){
            for(int j=i+1;j<screenChairs.size();j++){
                if(screenChairs.get(i).getY()>screenChairs.get(j).getY()){
                    ScreenChair screenChair=screenChairs.get(i);
                    screenChairs.set(i,screenChairs.get(j));
                    screenChairs.set(j,screenChair);
                }
            }
        }
        //在y轴不变的情况下按x轴排序
        for(int i=0;i<screenChairs.size()-1;i++){
            for(int j=i+1;j<screenChairs.size();j++){
                if(screenChairs.get(i).getY()==screenChairs.get(j).getY()){
                    if(screenChairs.get(i).getX()>screenChairs.get(j).getX()){
                        ScreenChair screenChair=screenChairs.get(i);
                        screenChairs.set(i,screenChairs.get(j));
                        screenChairs.set(j,screenChair);
                    }
                }

            }
        }
        hScreenService.addMuchChairs(screenChairs);
    }

    @RequestMapping("/showscreenchairs")
    @ResponseBody
    public List<SeatY> showScreenChairs(int sid){
        List<ScreenChair> screenChairs = hScreenService.findScreenChairBySid(sid);
        int maxX=0;
        int maxY=0;
        for(ScreenChair sc:screenChairs){
            if(sc.getX()>maxX){
                maxX=sc.getX();
            }
            if(sc.getY()>maxY){
                maxY=sc.getY();
            }
        }
        //把y坐标相同的放一个List中
        List<SeatY> lists=new ArrayList<>();
        for(int i=1;i<=maxY;i++){
            SeatY seatY=new SeatY();
            for(int j=0;j<screenChairs.size();j++){

                if(screenChairs.get(j).getY()==i){
                    seatY.setY(i);
                    seatY.add(screenChairs.get(j));
                }

            }
            lists.add(seatY);
        }
        return lists;
    }

    //与上方法无差但懒得封装
    @RequestMapping("/showscreenchairsinalert")
    @ResponseBody
    public List<SeatY> showScreenChairsInAlert(HttpSession session){
        int sid=(int)session.getAttribute("sid");
        if(sid==0){
            return null;
        }
        List<ScreenChair> screenChairs = hScreenService.findScreenChairBySid(sid);
        int maxX=0;
        int maxY=0;
        for(ScreenChair sc:screenChairs){
            if(sc.getX()>maxX){
                maxX=sc.getX();
            }
            if(sc.getY()>maxY){
                maxY=sc.getY();
            }
        }
        //把y坐标相同的放一个List中
        List<SeatY> lists=new ArrayList<>();
        for(int i=1;i<=maxY;i++){
            SeatY seatY=new SeatY();
            for(int j=0;j<screenChairs.size();j++){

                if(screenChairs.get(j).getY()==i){
                    seatY.setY(i);
                    seatY.add(screenChairs.get(j));
                }

            }
            lists.add(seatY);
        }
        return lists;
    }

    @RequestMapping("/addsid")
    public void addSid(int sid, HttpSession session){
        session.setAttribute("sid",sid);
    }

    @RequestMapping("/open")
    @ResponseBody
    public int openScreen(int sid){
        List<ScreenChair> screenChairByCid = hScreenService.findScreenChairBySid(sid);
        if(screenChairByCid.size()==0){
            return 0;
        }
        hScreenService.changeScreeenStatusBySid(sid,1);
        return 1;
    }

    @RequestMapping("/close")
    public void closeScreen(int sid){
        hScreenService.changeScreeenStatusBySid(sid,0);
    }


}

