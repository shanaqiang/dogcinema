package com.itwn.cinema.service;


import com.itwn.cinema.pojo.Film;
import com.itwn.cinema.pojo.MyLike;
import com.itwn.cinema.pojo.User;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.List;

public interface UserService {

    User selectByuser(User record);

    User selectByUname(String uname);

    int insertUser(User user);

    User selectByEmail(String email);

    void modifyheadimg(@RequestParam("uploadImg") MultipartFile uploadImg, HttpServletRequest req) throws FileNotFoundException;

    int modifyinfo(User user);

    //
    List<User> findAll();

    void banUserByid(int uid);

    void notsayByUid(int uid);

    void reduction(int uid);

    List<Film> selectMyLikeByUid(Integer uid);


    int cancelfilm(Integer fid,Integer uid);

    List<Order> selectMyOrderByUid(Integer uid);

    User selectByPrimaryKey(Integer uid);

    void updateVip(Integer uid);

    void insertmyLike(int uid,Integer fid);

    MyLike selectmyLike(Integer uid, Integer fid);


}
