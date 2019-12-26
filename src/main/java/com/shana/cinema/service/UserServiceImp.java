package com.shana.cinema.service;

import com.shana.cinema.mapper.UserMapper;
import com.shana.cinema.pojo.Film;
import com.shana.cinema.pojo.MyLike;
import com.shana.cinema.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImp implements UserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public User selectByuser(User record) {
        return userMapper.selectByuser(record);
    }

    @Override
    public User selectByUname(String uname) {
        return userMapper.selectByUname(uname);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public User selectByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    @Override
    public void modifyheadimg(@RequestParam("uploadImg") MultipartFile uploadImg, HttpServletRequest req) throws FileNotFoundException {
        User user= (User) req.getSession().getAttribute("user");
        //判断在前端用户添加了图片
        if(uploadImg.isEmpty()){
            return;
        }
        /*System.getProperty("user.dir")*/
        String path=req.getServletContext().getRealPath("/")+"front\\imgs\\header";
        String path1=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\front\\imgs\\header";
        String path2=System.getProperty("user.dir")+"\\target\\classes\\static\\front\\imgs\\header";
        System.out.println("path"+path);
        System.out.println("path1"+path1);
        System.out.println("path2"+path2);
        //上传至编译路径和项目路径
        //3等分的上传
        File f=new File(path);
        if (!f.exists()){
            f.mkdirs();
        }
        File f1=new File(path1);
        if (!f1.exists()){
            f1.mkdirs();
        }
        File f2=new File(path2);
        if (!f2.exists()){
            f2.mkdirs();
        }
        //给文件重命名
        String fileName=uploadImg.getOriginalFilename();
        UUID uuid=UUID.randomUUID();
        String suffix=fileName.substring(fileName.lastIndexOf("."));
        String newFileName=uuid.toString()+suffix;


        //文件夹名和文件名拼成
        File file=new File(f,newFileName);
        File file1=new File(f1,newFileName);
        File file2=new File(f2,newFileName);
        //开始上传
        FileOutputStream out=new FileOutputStream(file);
        FileOutputStream out1=new FileOutputStream(file1);
        try {
            byte[] img=uploadImg.getBytes();
            out.write(img);
            out.flush();
            out.close();
            out1.write(img);
            out1.flush();
            out1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //将图片保存至数据库
        user.setImg("/front/imgs/header"+newFileName);
        userMapper.modifyheadimg(newFileName,user.getUid());




        /*User user= (User) req.getSession().getAttribute("user");
        User use=userMapper.selectByPrimaryKey(user.getUid());
        String path = "front/imgs/header";
        File f= new File(path);
        String fileName=uploadImg.getOriginalFilename();
        UUID uuid=UUID.randomUUID();
        String suffix=fileName.substring(fileName.lastIndexOf("."));
        String newfileName=uuid.toString()+suffix;
        userMapper.modifyheadimg(newfileName,use.getUid());
        File newfile=new File(f.getAbsoluteFile()+File.separator+newfileName);
        try {
            uploadImg.transferTo(newfile);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public int modifyinfo(User user) {
        return userMapper.modifyinfo(user);
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public void banUserByid(int uid) {
        userMapper.setStatusByUid(0,uid);
    }

    @Override
    public void notsayByUid(int uid) {
        userMapper.setStatusByUid(2,uid);
    }

    @Override
    public void reduction(int uid) {
        userMapper.setStatusByUid(1,uid);
    }

    @Override
    public List<Film> selectMyLikeByUid(Integer uid) {
        return userMapper.selectMyLikeByUid(uid);
    }

    @Override
    public int cancelfilm(Integer fid, Integer uid) {
        return userMapper.cancelfilm(fid,uid);
    }

    @Override
    public List<Order> selectMyOrderByUid(Integer uid) {
        return userMapper.selectMyOrderByUid(uid);
    }

    @Override
    public User selectByPrimaryKey(Integer uid) {
        return userMapper.selectByPrimaryKey(uid);
    }

    @Override
    public void updateVip(Integer uid) {
         userMapper.updateVip(uid);
    }

    @Override
    public void insertmyLike(int uid, Integer fid) {
        userMapper.insertmyLike(uid,fid);
    }

    @Override
    public MyLike selectmyLike(Integer uid, Integer fid) {
        return userMapper.selectmyLike(uid,fid);
    }


}
