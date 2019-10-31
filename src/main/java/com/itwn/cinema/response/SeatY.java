package com.itwn.cinema.response;

import com.itwn.cinema.pojo.ScreenChair;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author xiana
 * @create 2019/10/22
 * @since 1.0.0
 */
@Data
public class SeatY {
    private int y;
    private List<ScreenChair> chairsX=new ArrayList<>();
    public void add(ScreenChair screenChair){
        this.chairsX.add(screenChair);
    }

    @Override
    public String toString() {
        return "SeatY{" +
                "y=" + y +
                ", chairsX=" + chairsX +
                '}';
    }
}

