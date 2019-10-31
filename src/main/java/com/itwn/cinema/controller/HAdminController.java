package com.itwn.cinema.controller;

import com.itwn.cinema.pojo.Administrator;
import com.itwn.cinema.pojo.Role;
import com.itwn.cinema.pojo.Roles;
import com.itwn.cinema.service.HIAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2019/10/25
 * @since 1.0.0
 */
@Controller
@RequestMapping("/hadmin")
public class HAdminController {
    @Autowired
    private HIAdminService adminService;

    //添加管理员
    @RequestMapping("/add")
    @ResponseBody
    public String addAdmin(Administrator admin){
        admin.setApass("123456");
        adminService.addAdmin(admin);
        return "添加成功,默认密码为123456";
    }

    //显示角色
    @RequestMapping("/showroles")
    @ResponseBody
    public List<Roles> showAllRole(){
        return adminService.showAllRole();
    }

    //修改管理员
    @RequestMapping("/set")
    @ResponseBody
    public String setAdmin(Administrator admin){
        adminService.setAdmin(admin);
        return "修改成功";
    }

    //查找单个管理员
    @RequestMapping("/showone")
    @ResponseBody
    public Administrator showOne(int aid){
        return adminService.findAdminById(aid);
    }

    //删除管理员
    @RequestMapping("/del/{id}")
    @ResponseBody
    public String del(@PathVariable("id") int aid){
        adminService.removeAdmin(aid);
        return "删除成功";
    }


}
