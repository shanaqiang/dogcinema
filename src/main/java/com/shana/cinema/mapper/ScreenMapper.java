package com.shana.cinema.mapper;

import com.shana.cinema.pojo.Screens;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ScreenMapper {

    @Insert(" insert into t_screens (sname,cbid,category,status,description) values (#{sname},#{cbid},#{category},0,#{description})")
    void insertScreen(Screens screens);

    @Select(" select * from t_screens where sname = #{sname}")
    Screens selectBySname(String sname);

    @Select(" select * from t_screens where cbid = #{value}")
    List<Screens> selectByCbid(int cbid);

    @Select(" select * from t_screens where sid = #{value}")
    Screens selectBySid(int sid);

    @Select(" select * from t_screens")
    List<Screens> selectAll();

    @Update(" update t_screens set status=#{status} where sid=#{sid}")
    void changeScreenStatusBySid(int sid,int status);



    @Select(" select * from t_screens where cbid = #{value}")
    List<Screens> selectByCbidAndStatus(int cbid);
}
