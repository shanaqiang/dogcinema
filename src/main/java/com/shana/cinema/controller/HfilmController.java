package com.shana.cinema.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shana.cinema.pojo.Film;
import com.shana.cinema.pojo.Qv;
import com.shana.cinema.pojo.ScreenFilm;
import com.shana.cinema.response.ResponseResult;
import com.shana.cinema.service.HIScreenFilmService;
import com.shana.cinema.service.HfilmServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author asus
 * @create 2019/10/19
 * @since 1.0.0
 */
@Controller
@RequestMapping("filmController")
public class HfilmController {

    @Autowired
    HfilmServiceImp hfilmServiceImp;
    @Autowired
    HIScreenFilmService screenFilmService;

    @RequestMapping(value = "/upload",method = {RequestMethod.POST})
    @ResponseBody
    public ResponseResult filmImg(@RequestParam(value="file",required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //该方法获取服务器路径
       // String path1=request.getServletContext().getRealPath("/filmimg");
        //存放图片的文件位置
        String path=System.getProperty("user.dir")+"/src/main/resources/static/filmimg";
        String path2=System.getProperty("user.dir")+"/target/classes/static/filmimg";
        File f=new File(path);
        File f2=new File(path2);
        if(!f.exists()){
            f.mkdirs();
        }
        if(!f2.exists()){
            f2.mkdirs();
        }
        String src="";
        //获取图片的名称
        if(file!=null){
            String originalName=file.getOriginalFilename();
            UUID uuid=UUID.randomUUID();
            String prefix=originalName.substring(originalName.lastIndexOf("."));
            String newFileName=uuid.toString()+prefix;
            File file1=new File(f,newFileName);
            File file2=new File(f2,newFileName);
            file.transferTo(file1);
            //file.transferTo(file2);
            FileInputStream fileInputStream = new FileInputStream(file1);
            FileOutputStream fileOutputStream = new FileOutputStream(file2,true);
            byte[] buf = new byte[1024*3];
            while((fileInputStream.read(buf))!=-1) {
                fileOutputStream.write(buf);
            }
            fileOutputStream.close();
            fileInputStream.close();
            src="/filmimg/"+newFileName;
        }
        ResponseResult responseResult=new ResponseResult("0","上传成功");
        responseResult.add("src",src);
        return responseResult;
    }


    //上架电影
    @RequestMapping("upperfilm")
    @ResponseBody
    public ResponseResult upperfilm(Film film){
        //System.out.println(film);
        hfilmServiceImp.addfilm(film);
        return new ResponseResult("200","成功上架");
    }

    //从数据库中select电影显示到modify.html
    @RequestMapping("modifyfilm")
    @ResponseBody
    public ResponseResult showFilm(@RequestParam(value = "pageNow",defaultValue = "1") int pageNow, Qv qv){
        //System.out.println(qv);
        PageHelper.startPage(pageNow,5);
        List<Film> list=hfilmServiceImp.findFilm(qv);
        PageInfo<Film> page=new PageInfo<>(list,3);
        return new ResponseResult("200","OK").add("pageInfo",page);
    }

    //回显选中的电影信息
    @RequestMapping("findfilm")
    @ResponseBody
    public Film findFilm(int fid){
        System.out.println(fid);
        Film film=hfilmServiceImp.findFilmByFid(fid);
        return film;
    }

    //修改电影信息
    @RequestMapping("updatefilm")
    @ResponseBody
    public ResponseResult updatefilm(Film film){
        System.out.println(film);
        hfilmServiceImp.changefilm(film);
        return new ResponseResult("200","修改成功");
    }

    //查询上映电影
    @RequestMapping("/showReleaseFilm")
    @ResponseBody
    public ResponseResult showReleaseFilm(String date){
        List<Film> lists = hfilmServiceImp.findFilmByRelease(date);
        //System.out.println(date);
        return new ResponseResult().add("films",lists);
    }


    //查询放映时间冲突
    @RequestMapping("/showReleaseTime")
    @ResponseBody
    public ResponseResult showReleaseTime(String filmid,String starttime,String screenid,String releasedate) throws ParseException {
        if(filmid.trim().equals("")||screenid.trim().equals("")||releasedate.trim().equals("")){
            return new ResponseResult("500","请选选择日期、影片、影厅");
        }
        int fid = Integer.parseInt(filmid);
        int sid = Integer.parseInt(screenid);
        Film film = hfilmServiceImp.findFilmByFid(fid);
        int minute = Integer.parseInt(film.getDuration());
        //System.out.println(minute);
        SimpleDateFormat sdf = new SimpleDateFormat("HH点mm分");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        //放映日期
        Date date3 = sdf1.parse(releasedate);
        //System.out.println(date3);
        Date date4 = new Date(date3.getTime()-24*3600*1000);
        //开始时间
        Date date5 = sdf.parse(starttime);
        //System.out.println(date5);
        Date date1 = new Date(sdf.parse(starttime).getTime()+date3.getTime());
        //System.out.println(date1);
        //结束时间
        Date date2 = new Date(date1.getTime()+(60*minute*1000));
        //System.out.println(starttime);
        //System.out.println(sdf.format(date));
        ScreenFilm sf = new ScreenFilm();
        sf.setScheduledate(date4);
        sf.setSid(sid);
        //System.out.println(date3);
        sf.setShowtime(date1);
        sf.setEndtime(date2);
        List<ScreenFilm> lists = screenFilmService.findScreenFilmByScreenAndTime(sf);
        //System.out.println(lists);
        for(ScreenFilm screenFilm : lists ){
            //System.out.println(screenFilm);
            if (date1.before(new Date(screenFilm.getEndtime().getTime()+screenFilm.getScheduledate().getTime()))&&
                    date2.after(new Date(screenFilm.getShowtime().getTime()+screenFilm.getScheduledate().getTime()))){
                return new ResponseResult("500","时间冲突");
            }
        }
        /*System.out.println(date2);
        System.out.println(date2.getTime());*/
        //System.out.println(date2);
        return new ResponseResult("200","1").add("endtime",date2.getTime()-date3.getTime());
    }


    @RequestMapping("showcount")
    @ResponseBody
    public int showcount(){
        int totalnum=hfilmServiceImp.findAllCount();
        return totalnum;
    }

}




