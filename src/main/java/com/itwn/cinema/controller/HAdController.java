package com.itwn.cinema.controller;

import com.itwn.cinema.pojo.Adevertisement;
import com.itwn.cinema.service.HIAdservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2019/10/27
 * @since 1.0.0
 */
@Controller
@RequestMapping("/had")
public class HAdController {
    @Autowired
    private HIAdservice adservice;

    @RequestMapping("/showall")
    @ResponseBody
    public List<Adevertisement> showall(){
        return adservice.findAll();
    }

    //添加广告
    @RequestMapping("/add")
    @ResponseBody
    public String add(MultipartFile uploadImg, Adevertisement ad, HttpServletRequest request) throws IOException {
        String path = request.getServletContext().getRealPath("/static/adimgs");
        File f = new File(path);
        if (!f.exists()){
            f.mkdir();
        }
        //获取图片名称
        String fileName = uploadImg.getOriginalFilename();
        UUID uuid = UUID.randomUUID();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = uuid.toString()+suffix;
        //创建图片对象
        File file = new File(f,newFileName);
        //上传
        uploadImg.transferTo(file);
        //设置图片名称
        ad.setImg(fileName);
        return "上传成功";
    }
}
