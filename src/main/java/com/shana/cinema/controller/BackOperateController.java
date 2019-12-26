package com.shana.cinema.controller;

import com.shana.cinema.pojo.Administrator;
import com.shana.cinema.pojo.CinemaBranch;

import com.shana.cinema.service.HIAdminService;
import com.shana.cinema.service.HICinemaBranchService;
import com.shana.cinema.utils.ImageVerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author asus
 * @create 2019/10/18
 * @since 1.0.0
 */
@Controller
@RequestMapping("backoperate")
public class BackOperateController {

    @Autowired
    HIAdminService adminService;

    @Autowired
    HICinemaBranchService hiCinemaBranchService;

    @RequestMapping("login")
    @ResponseBody
    public int login(Administrator administrator, String confirmword, HttpSession session) {
        String confirm = (String) session.getAttribute("confirm");
        if(confirmword.trim().equals("")){
            return -1;
        }
        if(confirmword==null){
            return -1;
        }
        if (!confirm.toUpperCase().equals(confirmword.toUpperCase())) {
            return -1;
        }

        Administrator admin = adminService.login(administrator);
        if (admin == null) {
            return 0;
        }
        session.setAttribute("huser", admin);
        return 1;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.setAttribute("huser","");
        return "redirect:/view/loginview";
    }

    @RequestMapping("getVerifiCode")
    @ResponseBody
    public void getVerifiCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
    /*
         1.生成验证码
         2.把验证码上的文本存在session中
         3.把验证码图片发送给客户端
         */
        ImageVerificationCode ivc = new ImageVerificationCode();     //用我们的验证码类，生成验证码类对象
        BufferedImage image = ivc.getImage();  //获取验证码
        request.getSession().setAttribute("confirm", ivc.getText()); //将验证码的文本存在session中
        ivc.output(image, response.getOutputStream());//将验证码图片响应给客户端
    }
    //删除影院
    @RequestMapping("delthreater")
    @ResponseBody
    public String  delthreater(Integer cdid){
        hiCinemaBranchService.delecteByCdid(cdid);
        return "刪除成功";
    }

    //查看所有影院列表
    @RequestMapping("/showthreater")
    @ResponseBody
    public List<CinemaBranch> showthreater(){
        return hiCinemaBranchService.findAll();
    }

    @RequestMapping("/showAllAdmin")
    @ResponseBody
    public List<Administrator> showAllAdmin(){
        return adminService.showAllAdmin();
    }

    //查看影院详情
    @RequestMapping("checkbrand")
    @ResponseBody
    public CinemaBranch checkbrand(Integer cdid){
        return hiCinemaBranchService.selectByPrimaryKey(cdid);
    }

    //修改影院信息
    @RequestMapping("modifybrand")
    public String  modifybrand(CinemaBranch cinemaBranch){
        hiCinemaBranchService.updataBrand(cinemaBranch);
        return "redirect:/showAllThreater";
    }
    //增加影院
    @RequestMapping("addbrand")
    public String addbrand(CinemaBranch cinemaBranch){
        hiCinemaBranchService.insertbrand(cinemaBranch);
        return "redirect:/view/showthreaterview";
    }

}
