package com.itwn.cinema.response;

import com.itwn.cinema.pojo.ScreenChair;
import com.itwn.cinema.pojo.ScreenSeats;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author xiana
 * @create 2019/10/24
 * @since 1.0.0
 */
@Data
public class SeatYa {
    private int y;
    private List<ScreenSeats> seatX=new ArrayList<>();
    public void add(ScreenSeats screenSeats){
        this.seatX.add(screenSeats);
    }

    @Override
    public String toString() {
        return "SeatY{" +
                "y=" + y +
                ", seatX=" + seatX +
                '}';
    }
}

