package com.shana.cinema.mapper;

import com.shana.cinema.pojo.Film;
import com.shana.cinema.pojo.MyLike;
import com.shana.cinema.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    int deleteByPrimaryKey(Integer uid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    User selectByPrimaryKey(Integer uid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    List<User> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    int updateByPrimaryKey(User record);

    @Select("select * from t_user where uname=#{uname} and upass=#{upass}")
    User selectByuser(User record);
    @Select("select * from t_user where uname=#{uname}")
    User selectByUname(String uname);
    @Insert("insert into t_user() values(#{uid},#{uname},#{upass},#{gender},#{img},#{email},#{tel},#{vip},#{vipdate},#{vipenddate},#{integral},#{status})")
    int insertUser(User user);
    @Select("select * from t_user where email=#{email}")
    User selectByEmail(String email);

    @Update("update t_user set  img=#{newfileName} where uid=#{uid}")
    int modifyheadimg(String newfileName,Integer uid);

    @Update("update t_user set tel=#{tel} where uid=#{uid}")
    int modifyinfo(User user);

    @Update(" update t_user set status=#{status} where uid=#{uid}")
    void setStatusByUid(int status,int uid);

    @Select("select * from t_film where fid in (select fid from mylike where uid=#{uid})")
    List<Film> selectMyLikeByUid(Integer uid);

    List<Order> selectMyOrderByUid(Integer uid);
    @Delete("delete from mylike where fid=#{fid} and uid=#{uid}")
    int cancelfilm(Integer fid,Integer uid);
    @Update("update t_user set vip=1 where uid=#{uid}")
    int updateVip(Integer uid);
    @Insert("insert into mylike(uid,fid) values(#{uid},#{fid})")
    int insertmyLike(int uid,Integer fid);

    @Select("select * from mylike where uid=#{uid} and fid=#{fid}")
     MyLike selectmyLike(Integer uid,Integer fid);

}
