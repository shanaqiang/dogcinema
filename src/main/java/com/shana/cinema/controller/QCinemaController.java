package com.shana.cinema.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shana.cinema.pojo.CinemaBranch;
import com.shana.cinema.pojo.Film;
import com.shana.cinema.pojo.ScreenFilm;
import com.shana.cinema.pojo.Screens;
import com.shana.cinema.response.ResponseResult;
import com.shana.cinema.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author xiana
 * @create 2019/10/23
 * @since 1.0.0
 */
@Controller
@RequestMapping("/qcinema")
public class QCinemaController {

    @Autowired
    QICinemaService qiCinemaService;
    @Autowired
    QICinemaService cinemaService;
    @Autowired
    HIScreenService screenService;
    @Autowired
    HICinemaBranchService cinemaBranchService;
    @Autowired
    HfilmService filmService;
    @Autowired
    HIScreenFilmService screenFilmService;


    //显示含有该影片的影院
    @RequestMapping("/showfilmcinemabranches")
    @ResponseBody
    public PageInfo showAllCinemas(@RequestParam(value = "pagenum",defaultValue = "1")int pagenum,
                                             @RequestParam(value = "pagesize",defaultValue = "6") int pagesize,
                                   int fid){
        if(fid==0){
            PageHelper.startPage(pagenum,pagesize);
            List<CinemaBranch> all = cinemaBranchService.findAll();
            PageInfo info=new PageInfo(all);
            return info;
        }
        //从t_screen_film(排片)中查询影片对应的影厅id
        List<ScreenFilm> screenFilm = screenService.findScreenFilmByFid(fid);
        //根据影厅id从t_screen中查询所有影厅
        //有些排片中有影厅信息但是影厅被删除，就会报空指针异常
        List<Screens> screens=new ArrayList<>();
        for(ScreenFilm s:screenFilm){
            Screens screen=screenService.findScreenBySid(s.getSid());
            if(screens!=null){
                screens.add(screen);
            }
        }
        //根据影厅中的分店id（cbid）查询所有分店
        //有些影厅分店被删除但是影厅仍然存在，就会报null pointer
        //开启分页
        PageHelper.startPage(pagenum,pagesize);
        Set<CinemaBranch> cinemaBranches=new HashSet<>();
        for(Screens s:screens){
            if(s!=null){
                CinemaBranch cb=cinemaBranchService.findByCbid(s.getCbid());
                if(cb!=null){
                    cinemaBranches.add(cb);
                }
            }
        }


        List<CinemaBranch> cinemaBranches1=new ArrayList<>();
        for(CinemaBranch c:cinemaBranches){
            if(cinemaBranches1.size()==0){
                cinemaBranches1.add(c);
            }else{
                for(CinemaBranch cc:cinemaBranches1){
                    if(cc.getCdname().equals(c.getCdname())){
                        break;
                    }
                    cinemaBranches1.add(c);
                }
            }

        }
        PageInfo info=new PageInfo(cinemaBranches1);
        return info;
    }

    @RequestMapping("/showone/{cdid}/{fid}")
    public String showone(@PathVariable(value = "cdid") int cdid,
                          @PathVariable(value = "fid") int fid,HttpServletRequest request){
        request.setAttribute("cdid",cdid);
        request.setAttribute("fid",fid);
        return "front/cinemadetails";
    }

    //点击影片跳转至含有该影片的影院
    @RequestMapping("/gocinemabranches/{fid}")
    public String showFilmCinemaBranches(@PathVariable(value = "fid") int fid,HttpServletRequest request){
        Film film = filmService.findFilmByFid(fid);
        request.setAttribute("film",film);
        request.setAttribute("fid",fid);
        return "front/filmcinemas";
    }


    //暂停施工
    @RequestMapping("/gocinemabranch")
    public String showFilmCinemaBranches(HttpServletRequest request){
        request.setAttribute("fid",0);
        return "front/cinemas";
    }

    //去某个影院界面
    @RequestMapping("/goonecinemabranch/{cbid}")
    public String goOneCinemaBranch(@PathVariable(value = "cbid")int cbid,HttpServletRequest request){
        request.setAttribute("cbid",cbid);
        return "front/onecinemabranch";
    }

    //显示某个影院的所有影片(以及影院信息)
    @RequestMapping("/showallfilmsbycbid")
    @ResponseBody
    public ResponseResult showAllFilmsByCbid(int cbid){
        ResponseResult rs=new ResponseResult();
        //查询影院信息放入rs
        CinemaBranch cinemaBranch = cinemaBranchService.findByCbid(cbid);
        rs.add("cinemabranch",cinemaBranch);
        //查询影院的所有影厅
        List<Screens> screens= screenService.findScreensByCbid(cbid);
        //用来存放影片id
        List<Integer> fids=new ArrayList<>();
        //查看所有影厅所有的影片
        for(Screens s:screens){
            List<ScreenFilm> screenFilms = screenFilmService.findBySid(s.getSid());
            //提取所有的fid
            for(ScreenFilm sf:screenFilms){
                if(fids.contains(sf.getFid())){
                    continue;
                }
                fids.add(sf.getFid());
            }

        }
        List<Film> films=new ArrayList<>();
        for(int fid:fids){
            Film film = filmService.findFilmByFid(fid);
            films.add(film);
        }
        //将电影添加到rs中
        rs.add("films",films);
        return rs;

    }



}

