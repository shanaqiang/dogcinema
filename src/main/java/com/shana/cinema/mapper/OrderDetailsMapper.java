package com.shana.cinema.mapper;

import com.shana.cinema.pojo.OrderDetails;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface OrderDetailsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_details
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    int deleteByPrimaryKey(Integer odid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_details
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    int insert(OrderDetails record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_details
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    OrderDetails selectByPrimaryKey(Integer odid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_details
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    List<OrderDetails> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_details
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    int updateByPrimaryKey(OrderDetails record);

    //orderdetails添加数据
    @Insert("insert into t_order_details (oid,ssid,status) values(#{oid},#{ssid},#{status})")
    void insertorderdetail(int oid,String ssid,int status);

    @Update("update t_order_details set status=2 where oid=#{oid}")
    void updateorderdetailsstatus(int oid);

    @Select("select ssid from t_order_details where oid=#{oid}")
    List<Integer> selectssidbyoid(int oid);

    @Select("select count(*) from t_order_details where ssid=#{ssid} and status=1")
    int selectcount(String ssid);

    @Select("select * from t_order_details where ssid=#{ssid} and status=0")
    List<OrderDetails> selectOrderDetailsStatus0(String ssid);
}