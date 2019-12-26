package com.shana.cinema.response;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author xiana
 * @create 2019/10/23
 * @since 1.0.0
 */
@Data
@Component
public class FilmLimit3 {
    private String country;
    private String type;
    private String year;

    @Override
    public String toString() {
        return "FilmLimit3{" +
                "country='" + country + '\'' +
                ", type='" + type + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}

