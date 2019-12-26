package com.shana.cinema.controller;

import com.shana.cinema.pojo.User;
import com.shana.cinema.service.UserService;
import com.shana.cinema.utils.Mailutil;
import com.shana.cinema.utils.VerifyCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2019/10/23
 * @since 1.0.0
 */
@Controller
@RequestMapping("/quser")
public class QUserController {
    @Autowired
    UserService userService;
    @Autowired
    Mailutil mailutil;
    //登录验证
    @RequestMapping("/loginuname")
    @ResponseBody
    public String loginUname(User user, String codeImg, HttpServletRequest req, Map map) {
        String codeimg = (String) req.getSession().getAttribute("codeimg");
        if(codeImg==null){
            return "请输入验证码";
        }
        User user1=userService.selectByUname(user.getUname());
        if(user1!=null){
            if(user1.getStatus()==0){
                return "您已被封号";
            }
        }
        if (!codeImg.toUpperCase().equals(codeimg.toUpperCase())) { return "验证码有误,请重新输入";}
        User use = userService.selectByuser(user);
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUname(), user.getUpass());
        // 执行认证登陆
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return "未知账户";
        } catch (IncorrectCredentialsException ice) {
            return "密码不正确";
        } catch (LockedAccountException lae) {
            return "账户已锁定";
        } catch (ExcessiveAttemptsException eae) {
            return "用户名或密码错误次数过多";
        } catch (AuthenticationException ae) {
            return "用户名或密码不正确！";
        }
        if (subject.isAuthenticated()) {
            req.getSession().setAttribute("userinfo",use);
            req.getSession().setAttribute("user",use);
            return "登录成功";
        } else {
            token.clear();
            return "登录失败";
        }
    }
    //邮箱登录
    @RequestMapping("/loginmail")
    @ResponseBody
    public String loginMail(String email,String codeImg, HttpServletRequest req, Map map,String codeemail) {
        String codeimg = (String) req.getSession().getAttribute("codeimg");
        String codemail= (String) req.getSession().getAttribute("codemail");
        if (!codeImg.toUpperCase().equals(codeimg.toUpperCase())) {
            return "验证码有误,请重新输入"; }
        if (!codeemail.equals(codemail)) {
            return "邮箱验证吗错误"; }
        req.getSession().setAttribute("userinfo",userService.selectByEmail(email));
        return "登录成功";
    }
    //登录时获取邮箱验证吗
    @RequestMapping("/getlogincode")
    @ResponseBody
    public String saveloginCode(String email,HttpServletRequest req) {
        if (!email.matches("^\\w+@(\\w+\\.)+\\w+$")) {
            return  "请输入正确的邮箱格式";
        }else if(userService.selectByEmail(email)==null){
            return "该邮箱还没有被注册";
        }else{
            return mailutil.mailutil(email,req);
        }
    }

    //注册时获取邮箱验证码
    @RequestMapping("/getregistercode")
    @ResponseBody
    public String saveregisterCode(String email, Map map,HttpServletRequest req) {
        if (!email.matches("^\\w+@(\\w+\\.)+\\w+$")) {
            return  "请输入正确的邮箱格式";
        }else if(userService.selectByEmail(email)!=null){
            return "该邮箱已被使用,请换一个邮箱注册";
        }else{
            return mailutil.mailutil(email,req);
        }
    }

    //验证码的生成与更换
    @RequestMapping("/picture")
    public void picture(HttpServletResponse resp, HttpServletRequest req) throws IOException {
        VerifyCode img = new VerifyCode();
        BufferedImage image = img.getImage();
        req.getSession().setAttribute("codeimg",img.getText());
        ImageIO.write(image, "jpg", resp.getOutputStream());
    }

    //注册时验证账户名是否已存在
    @RequestMapping("/registerUname")
    @ResponseBody
    public String logonName(String uname){
        if(userService.selectByUname(uname)==null){
            return "该账户还没有人使用ε=(´ο｀*)))唉"; }
        return "账户名已存在,换一个试试呗";
    }

    //注册时验证手机号码格式是否正确
    @RequestMapping("/registertel")
    @ResponseBody
    public String registerphone(String tel){
        if(!tel.matches("^[1](([3|5|8][\\\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\\\d]{8}$")){
            return "你的手机号码格式错误了"; }
        return "手机号码格式正确";
    }

    @RequestMapping("/registeremail")
    @ResponseBody
    public String registeremail(String email){
        if (!email.matches("^\\w+@(\\w+\\.)+\\w+$")) {
            return  "请输入正确的邮箱格式";
        }else if(userService.selectByEmail(email)!=null){
            return "该邮箱已被使用,请换一个邮箱注册";
        }else{
            return "该邮箱可以使用";
        }
    }

    //注册时的一些验证
    @RequestMapping("/register")
    @ResponseBody
    public String logon(User user,String codeMail,HttpServletRequest req) throws IOException {
        if(userService.selectByUname(user.getUname())!=null){
            return "账户名已存在,换一个试试呗"; }
        Date createtime= (Date) req.getSession().getAttribute("createtime");
        //System.out.println(createtime);
        if(createtime==null){
            return "请获取验证码";
        }
        String codemail= (String) req.getSession().getAttribute("codemail");
        long second= new Date().getTime()-createtime.getTime();
        if (second/1000>60) {
            return "邮箱验证码已失效请重新发送"; }
        if (!codeMail.equals(codemail)){
            return "邮箱验证码有误,请重新输入"; }
        user.setVip(0);
        user.setStatus(1);
        userService.insertUser(user);
        return "注册成功";
    }
}
