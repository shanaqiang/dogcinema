package com.shana.cinema.response;

import com.shana.cinema.pojo.CinemaBranch;
import com.shana.cinema.pojo.Screens;
import lombok.Data;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author xiana
 * @create 2019/10/22
 * @since 1.0.0
 */
@Data
public class ScreenQv {
    private Screens screens;
    private CinemaBranch cinemaBranch;
}

