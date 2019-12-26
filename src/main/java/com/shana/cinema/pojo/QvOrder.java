package com.shana.cinema.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author asus
 * @create 2019/10/25
 * @since 1.0.0
 */
@Data
public class QvOrder {
    private int oid;
    private int uid;
    private String fname;
    private String serialnum;
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern="HH:mm")
    private Date showtime;
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern="HH:mm")
    private Date endtime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy年MM月dd日")
    private Date scheduledate;
    private String sname;
    private int nowprice;
    private int x;
    private int y;
    private int status;

}
