package com.itwn.cinema.service;

import com.itwn.cinema.pojo.Orders;
import com.itwn.cinema.pojo.QvOrder;

import java.util.Date;
import java.util.List;

public interface QorderService {
    int findSfidBySsid(String firstssid);
    int addorder(Orders order);
    void addOrderDetail(int oid,String ssid,int status);
    void setScreenseatsStatus(String ssid,Date createdate);
    int checkordersstatus(int oid);
    void changeordersstatus(int oid);
    void changeorderdetailsstatus(int oid);
    List<Integer> findssidbyoid(int oid);
    void changeseatstatusbyssid(int ssid);
    List<QvOrder> findlastorder(int uid);
    void changeStatus1(int oid);
    int findcount(String ssid);

}
