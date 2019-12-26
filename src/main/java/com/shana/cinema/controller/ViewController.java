package com.shana.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author asus
 * @create 2019/10/17
 * @since 1.0.0
 */
@Controller
@RequestMapping("view")
public class ViewController {

    //登录页面
    @RequestMapping("loginview")
    public String loginview(){
        return "background/login";
    }

    //主页面
    @RequestMapping("mainview")
    public String mainview(){
        return "background/index";
    }

    //上架电影
    @RequestMapping("upperview")
    public String upperview(){
        return "background/uppershelf";
    }

    //下架电影
    @RequestMapping("lowerview")
    public String lowerview(){
        return "background/lowershelf";
    }

   //修改电影信息
    @RequestMapping("modifyview")
    public String modifyview(){
        return "background/modify";
    }

    //查询电影信息
    @RequestMapping("checkfilmview")
    public String addfilm(){
        return "background/checkfilm";
    }

    //排片页面
    @RequestMapping("arrangefilmview")
    public String arrangefilm(){
        return "background/arrangefilm";
    }

    //用户管理页面
    @RequestMapping("usermanageview")
    public String usermanage() {
        return "background/usermanage";
    }

    //查询影厅信息页面
    @RequestMapping("showhallview")
    public String showhall(){
        return "background/showhall";
    }

    //设置影厅页面
    @RequestMapping("sethallview")
    public String sethall(){
        return "background/sethall";
    }

    //新增影厅页面
    @RequestMapping("addhallview")
    public String addhall(){
        return "background/addhall";
    }

    //影院管理
    @RequestMapping("cinemamanageview")
    public String cinemamanage(){
        return "background/cinemamanage";
    }

    //登录界面
    @RequestMapping("/frontlogin")
    public String viewlogin(){
        return "login";
    }

    //员工管理
    @RequestMapping("adminmanageview")
    public String adminmanage(){
        return "background/adminmanage";
    }

    //广告管理
    @RequestMapping("advertview")
    public String advertmanage(){
        return "background/advert";
    }
    //系统管理
    @RequestMapping("showthreaterview")
    public String showthreaterview(){
        return "background/showthreater";
    }

    //增加影院
    @RequestMapping("addthreaterview")
    public String addthreaterview(){
        return "background/addthreater";
    }

    @RequestMapping("showAllThreater")
    public String showAllThreater(){
        return "background/showAllThreater";
    }

    @RequestMapping("/showalertseats")
    public String showAlertSeats(){
        return "background/screenseats";
    }



}
