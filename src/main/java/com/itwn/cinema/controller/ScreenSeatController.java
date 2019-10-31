package com.itwn.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2019/10/19
 * @since 1.0.0
 */
@Controller
@RequestMapping("/backgroundScreenSeat")
public class ScreenSeatController {

    @RequestMapping("/addSeat")
    @ResponseBody
    public void addSeat(String[] seat){
        System.out.println(seat[0]);
    }

}
