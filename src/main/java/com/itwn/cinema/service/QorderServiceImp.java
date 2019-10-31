package com.itwn.cinema.service;

import com.itwn.cinema.mapper.OrderDetailsMapper;
import com.itwn.cinema.mapper.OrdersMapper;
import com.itwn.cinema.mapper.ScreenSeatsMapper;
import com.itwn.cinema.pojo.OrderDetails;
import com.itwn.cinema.pojo.Orders;
import com.itwn.cinema.pojo.QvOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author asus
 * @create 2019/10/24
 * @since 1.0.0
 */
@Service
public class QorderServiceImp implements QorderService{
    @Autowired
    ScreenSeatsMapper screenSeatsMapper;
    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    OrderDetailsMapper orderdetailsMapper;
    @Override
    public int findSfidBySsid(String firstssid) {
        int sfid=screenSeatsMapper.selectsfidByssid(firstssid);
        return sfid;
    }

    @Override
    public void setScreenseatsStatus(String ssid, Date createdate) {
        screenSeatsMapper.updateScreenSeats(ssid,createdate);
    }

    @Override
    public int checkordersstatus(int oid) {
        int status=ordersMapper.selectstatusbyoid(oid);
        return status;
    }

    @Override
    public void changeordersstatus(int oid) {
        ordersMapper.updatestatus(oid);
    }

    @Override
    public void changeorderdetailsstatus(int oid) {
        orderdetailsMapper.updateorderdetailsstatus(oid);
    }

    @Override
    public List<Integer> findssidbyoid(int oid) {
        List<Integer> listssid=orderdetailsMapper.selectssidbyoid(oid);
        return listssid;
    }

    @Override
    public void changeseatstatusbyssid(int ssid) {
        screenSeatsMapper.updateseatstatusbyssid(ssid);
    }

    @Override
    public List<QvOrder> findlastorder(int uid) {
        List<QvOrder> list= ordersMapper.selectlastorder(uid);
        return list;
    }

    @Override
    public void changeStatus1(int oid) {
        ordersMapper.updatestatus1(oid);
    }

    @Override
    public int findcount(String ssid) {
        //状态等于1
       int count=orderdetailsMapper.selectcount(ssid);
       //状态等于0
        List<OrderDetails> orderDetails=orderdetailsMapper.selectOrderDetailsStatus0(ssid);
        for(OrderDetails od:orderDetails){
            //寻找orderdetails对应的order
            int oid=od.getOid();
            Orders orders = ordersMapper.selectByPrimaryKey(oid);
            //时间小于10分钟
            Date createdate = orders.getCreatedate();
            if((new Date()).getTime()-createdate.getTime()<10*60*1000){
                count++;
            }

        }
        return count;
    }

    @Override
    public int addorder(Orders order) {
        int oid=ordersMapper.insertorder(order);
        return oid;
    }

    @Override
    public void addOrderDetail(int oid, String ssid, int status) {
        orderdetailsMapper.insertorderdetail(oid,ssid,status);
    }




}
