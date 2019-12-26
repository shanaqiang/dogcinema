package com.shana.cinema.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shana.cinema.pojo.User;
import com.shana.cinema.response.ResponseResult;
import com.shana.cinema.service.UserService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author xiana
 * @create 2019/10/19
 * @since 1.0.0
 */
@Controller
@RequestMapping("/user")
public class HUserController {

    @Autowired
    UserService userService;
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @RequestMapping("/show")
    @ResponseBody
    public ResponseResult showUser(@RequestParam(value = "pagenum",defaultValue = "1") int pagenum,
                                   @RequestParam(value = "pagesize",defaultValue = "3") int pagesize){
        PageHelper.startPage(pagenum,pagesize);
        List<User> users = userService.findAll();
        PageInfo<User> info=new PageInfo<>(users);

        ResponseResult responseResult=new ResponseResult();
        responseResult.add("pageinfo",info);
        //pageinfo里面包含查询到的users信息
        return responseResult;
    }


    @RequestMapping("/banuser")
    @ResponseBody
    public String banuser(int uid){
        userService.banUserByid(uid);
        return "成功封禁用户";
    }

    @RequestMapping("/notsay")
    @ResponseBody
    public String notsay(int uid){
        userService.notsayByUid(uid);
        return "成功禁言用户";
    }

    @RequestMapping("/reduction")
    @ResponseBody
    public String reduction(int uid){
        userService.reduction(uid);
        return "成功还原用户状态";
    }
}

