package com.shana.cinema.pojo;

import lombok.Data;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author xiana
 * @create 2019/10/21
 * @since 1.0.0
 */
@Data
public class ScreenChair {
    private int scid;
    private int sid;
    private int x;
    private int y;
    private int status;
    private String description;

    @Override
    public String toString() {
        return "ScreenChair{" +
                "scid=" + scid +
                ", sid=" + sid +
                ", x=" + x +
                ", y=" + y +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }
}

