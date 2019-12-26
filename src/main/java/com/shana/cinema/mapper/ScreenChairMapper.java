package com.shana.cinema.mapper;

import com.shana.cinema.pojo.ScreenChair;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ScreenChairMapper {


    @Insert(" insert into t_screen_chair (sid,x,y,status,description) values(#{sid},#{x},#{y},#{status},#{description})")
    void insert(ScreenChair screenChair);

    @Delete(" delete from t_screen_chair where sid=#{value}")
    void deleteBySid(int sid);

    @Select(" select count(1) from t_screen_chair where sid=#{value}")
    int selectCountBySid(int sid);

    @Select(" select * from t_screen_chair where sid=#{value}")
    List<ScreenChair> selectBySid(int sid);
}
