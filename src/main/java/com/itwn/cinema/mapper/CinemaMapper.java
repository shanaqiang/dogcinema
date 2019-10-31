package com.itwn.cinema.mapper;

import com.itwn.cinema.pojo.Cinema;
import java.util.List;

public interface CinemaMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cinema
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    int deleteByPrimaryKey(Integer cid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cinema
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    int insert(Cinema record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cinema
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    Cinema selectByPrimaryKey(Integer cid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cinema
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    List<Cinema> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cinema
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    int updateByPrimaryKey(Cinema record);
}