package com.itwn.cinema.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itwn.cinema.pojo.Film;
import com.itwn.cinema.pojo.User;
import com.itwn.cinema.service.UserService;
import com.itwn.cinema.utils.Mailutil;
import com.itwn.cinema.utils.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class Usercontroller {
    @Autowired
    UserService userService;
    @Autowired
    Mailutil mailutil;
    //登录验证
    @RequestMapping("/loginuname")
    public String loginUname(User user,String codeImg, HttpServletRequest req, Map map) {
        String codeimg = (String) req.getSession().getAttribute("codeimg");
        if (!codeImg.equals(codeimg)) {
            map.put("imginfo", "验证码有误,请重新输入"); }
        User use = userService.selectByuser(user);
        if (use == null) {
            map.put("errorinfo", "账号不存在,或者密码错误"); }
        if(map==null){
            req.getSession().setAttribute("user",use);
            return "index"; }
        return "login";
    }


    //邮箱登录
    @RequestMapping("/loginmail")
    public String loginMail(String email,String codeImg, HttpServletRequest req,Map map,String codeemail) {
        String codeimg = (String) req.getSession().getAttribute("codeimg");
        String codemail= (String) req.getSession().getAttribute("codemail");
        if (!codeImg.equals(codeimg)) {
            map.put("imginfo", "验证码有误,请重新输入"); }
        if (!codeemail.equals(codemail)) {
            map.put("errorinfo", "邮箱验证吗错误"); }
        if(map==null){
            req.getSession().setAttribute("user",userService.selectByEmail(email));
            return "index"; }
        return "login";
    }


    //登录时验证邮箱格式和是否存在
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
    public String saveregisterCode(String mail, Map map,HttpServletRequest req) {
        return mailutil.mailutil(mail,req);
    }

    //验证码的生成与更换
    @RequestMapping("/picture")
    public void picture(HttpServletResponse resp,HttpServletRequest req) throws IOException {
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
            return "该账户还没有人使用ε=(´ο｀*)))唉"; };
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
    public String logon(User user,String codeMail,String codeImg,Map map,HttpServletRequest req,@RequestParam("uploadImg") MultipartFile uploadImg) throws IOException {
        String codeimg = (String) req.getSession().getAttribute("codeImg");
        Date createtime= (Date) req.getSession().getAttribute("createtime");
        String codemail= (String) req.getSession().getAttribute("codemail");
        if (!codeImg.equals(codeimg)) {
            map.put("imginfo", "验证码有误,请重新输入"); }
        long second= new Date().getTime()-createtime.getTime();
        if (second/1000>60) {
            map.put("mailinfo","邮箱验证码已失效请重新发送"); }
        else if (!codeMail.equals(codemail)){
            map.put("mailinfo","邮箱验证码有误,请重新输入"); }
        if(map==null){
        //在表单上需要加 enctype="multipart/form-data"  属性
            String path = req.getServletContext().getRealPath("/images");
            File f = new File(path);
            if (!f.exists()){
                f.mkdir();
            }
            String fileName = uploadImg.getOriginalFilename();
            UUID uuid = UUID.randomUUID();
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = uuid.toString()+suffix;
            File file = new File(f,newFileName);
            uploadImg.transferTo(file);
            user.setImg(newFileName);
            userService.insertUser(user);
            return "login"; }
        return "register";
    }


    //查看个人中心
    @RequestMapping("/perinfo")
    @ResponseBody
    public User myinfo( HttpServletRequest req){
        User use= (User) req.getSession().getAttribute("user");
        User user=userService.selectByPrimaryKey(use.getUid());
        return  user;
    }

   //修改头像
   @RequestMapping("modifyheadimg")
    public String modifyheadimg(@RequestParam("uploadImg") MultipartFile uploadImg, HttpServletRequest req, Model model) throws FileNotFoundException {
       int se=0;
        if(uploadImg.isEmpty()){
            se=1;
        }else{
            userService.modifyheadimg(uploadImg,req);
        }
        User user1= (User) req.getSession().getAttribute("user");
        int uid= user1.getUid();
       User user = userService.selectByPrimaryKey(uid);
       req.getSession().setAttribute("user",user);
       req.getSession().setAttribute("userinfo",user);
       /*return "redirect:/modifymyinfopage?se="+se;*/
       return  "redirect:/myinfopage";
   }
   //修改个人信息
    @RequestMapping("modifyinfo")
    public String modifyinfo(User user){
        userService.modifyinfo(user);
        return "front/myinfopage";
    }
    //查看我的收藏
    @RequestMapping("mylike")
    @ResponseBody
    public PageInfo<Film> mylike(@RequestParam(value="pageNow",defaultValue="1")int pageNow,HttpServletRequest request){
        PageHelper.startPage(pageNow,8);
        User user= (User) request.getSession().getAttribute("user");
        List<Film> list=userService.selectMyLikeByUid(user.getUid());
        PageInfo<Film> pageInfo=new PageInfo<>(list,0);
        return  pageInfo;
    }

    //取消收藏
    @RequestMapping("cancelfilm")
    @ResponseBody
    public String cancelfilm(Integer fid,HttpServletRequest request){
        User user= (User) request.getSession().getAttribute("user");
       userService.cancelfilm(fid,user.getUid());
        return "取消收藏成功";
    }

    //查看我的订单
    @RequestMapping("myorder")
    @ResponseBody
    public List<Order> muorder(HttpServletRequest request){
        User user= (User) request.getSession().getAttribute("user");
        return userService.selectMyOrderByUid(user.getUid());
    }

    //添加收藏
    @RequestMapping("addmylike")
    @ResponseBody
    public String addmylike(HttpServletRequest request,Integer fid){
        User user= (User) request.getSession().getAttribute("user");
        if(userService.selectmyLike(user.getUid(),fid)==null){
            userService.insertmyLike(user.getUid(),fid);
            return "收藏成功";
        }
        return "已经收藏过了";
    }
    @RequestMapping("outlogin")
    public String outlogin(HttpSession session){
        session.removeAttribute("user");
        return "front/login";
    }
}
