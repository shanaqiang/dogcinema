package com.shana.cinema.controller;

import com.shana.cinema.pojo.FilmComent;
import com.shana.cinema.pojo.User;
import com.shana.cinema.service.QIFilmComentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2019/10/24
 * @since 1.0.0
 */
@Controller
@RequestMapping("/qcoment")
public class QFilmComentController {
    @Autowired
    private QIFilmComentService comentService;

    @RequestMapping("/addcoment")
    @ResponseBody
    public String addComent(FilmComent coment, HttpSession session){
        User user = (User)session.getAttribute("userinfo");
        if(user==null){
            return "未登录";
        }
        coment.setUid(user.getUid());
        coment.setCreaetedate(new Date());
        coment.setStars(coment.getScore());
        coment.setStatus(1);
        comentService.addComent(coment);
        return "添加成功";
    }

    @RequestMapping("/showcoment/{fid}")
    @ResponseBody
    public List<FilmComent> showComent(@PathVariable(value = "fid") int fid){
        return comentService.findComentByFilmId(fid);
    }
}
