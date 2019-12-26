package com.shana.cinema.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author asus
 * @create 2019/10/21
 * @since 1.0.0
 */
@Data
public class Qv {
    private String fname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releasedate;
    private int status;
    private int recommend;

    @Override
    public String toString() {
        return "Qv{" +
                "fname='" + fname + '\'' +
                ", releasedate=" + releasedate +
                ", status=" + status +
                ", recommend=" + recommend +
                '}';
    }
}
