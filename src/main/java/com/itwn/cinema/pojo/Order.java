package com.itwn.cinema.pojo;

import com.itwn.cinema.pojo.Film;
import com.itwn.cinema.pojo.ScreenFilm;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Order {
    private Integer oid;

    private Integer uid;

    private Integer sfid;

    private Date createdate;

    private String serialnum;

    private Integer status;

    private String description;


    private ScreenFilm screenFilm;

    private Film film;

    private List<OrderDetails> list;

    private Screens screens;

    private CinemaBranch cinemaBranch;
 }
