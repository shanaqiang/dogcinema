package com.shana.cinema.mapper;

import com.shana.cinema.pojo.Film;
import com.shana.cinema.pojo.Qv;
import com.shana.cinema.response.FilmLimit3;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FilmMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_film
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    int deleteByPrimaryKey(Integer fid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_film
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    int insert(Film record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_film
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    Film selectByPrimaryKey(Integer fid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_film
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    List<Film> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_film
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    int updateByPrimaryKey(Film record);

    //查询热播电影
    List<Film> selectHotFilmByStatusAndRecommend();

    //查询即将上映的电影
    List<Film> selectCommingFilmByStatusAndRecommend();

    //票行排行显示
    List<Film> selectFilmOrderByBoxoffice();

    //评分排行显示
    List<Film> selectFilmOrderByScore();

    //预播评论数排行
    List<Film> selectFilmOrderByGradecount();

    //查询电影数目
    int selectcount();

    @Select("select * from t_film where releasedate <= #{value} and status = 1")
    List<Film> selectFilmByRelease(String date);

    List<Film> selectFilmByFnameAndReleasedateAndStatusAndrecommend(@Param("qv") Qv qv);

    @Select("select * from t_film where fid=#{fid}")
    Film selectFilmByFid(int fid);

    List<Film> selectByFilmLimit3(@Param("filmlimit3") FilmLimit3 filmLimit3);

    @Select(" select * from t_film where fname like '%${fname}%'")
    List<Film> selectByFname(String fname);

    @Select(" select * from t_film where type like '%${type}%'")
    List<Film> selectByType(String type);

}