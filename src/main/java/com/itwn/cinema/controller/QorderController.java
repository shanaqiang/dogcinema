package com.itwn.cinema.controller;

import com.itwn.cinema.pojo.Orders;
import com.itwn.cinema.pojo.QvOrder;
import com.itwn.cinema.pojo.User;
import com.itwn.cinema.service.QorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author asus
 * @create 2019/10/24
 * @since 1.0.0
 */

@Controller
@RequestMapping("order")
public class QorderController {

    static ExecutorService fixedThreadPool= Executors.newCachedThreadPool();
    @Autowired
    QorderService qorderService;

    @RequestMapping("createorder")
    @Transactional
    public String createorder(String ssidstring, HttpSession session,Map<String,Object> map){
        User user= (User) session.getAttribute("userinfo");
        if(user==null){
            System.out.println("检测不到用户");
            return "redirect:/";
        }
        map.put("user",user);
        int uid=user.getUid();
        String[] ssidArray=ssidstring.split(",");
        //查询数据库是否有未支付状态的ssid座位重复
        for(int i=0;i<ssidArray.length;i++){
           int count= qorderService.findcount(ssidArray[i]);
           if(count!=0){
               System.out.println("检测座位有人");
               return "redirect:/";
           }
        }
        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    //当前时间作为订单生成时间
                    Date createdate=new Date();
                    //将screen_seat中的status设为3,并将notpaydate设为当前时间
                    for(int i=0;i<ssidArray.length;i++){
                        qorderService.setScreenseatsStatus(ssidArray[i],createdate);
                    }
                    //根据ssid拿到sfid排片号
                    String firstssid=ssidArray[0];
                    int sfid=qorderService.findSfidBySsid(firstssid);
                    //订单状态为未付款状态
                    int status=0;
                    //获取当前毫秒值加上用户id生成流水号serialnum
                    long millValue=System.currentTimeMillis();
                    String userid=uid+"";
                    String serialnum=millValue+userid;
                    //将上述数据插入t_orders同时返回它的oid
                    Orders order=new Orders(uid,sfid,createdate,serialnum,status,null);
                    int num=qorderService.addorder(order);
                    //System.out.println(num);
                    int oid=order.getOid();
                    System.out.println(oid);
                    //将数据插入到订单明细中
                    for(int i=0;i<ssidArray.length;i++){
                        qorderService.addOrderDetail(oid,ssidArray[i],status);
                    }
                    Thread.sleep(600000);
                    int nowstatus=qorderService.checkordersstatus(oid);
                    if(nowstatus==0){
                        qorderService.changeordersstatus(oid);
                        qorderService.changeorderdetailsstatus(oid);
                        List<Integer> listssid=qorderService.findssidbyoid(oid);
                        for(int i=0;i<listssid.size();i++){
                            qorderService.changeseatstatusbyssid(listssid.get(i));
                        }
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        return "front/noworderpage";
    }

    @RequestMapping("findnoworder")
    @ResponseBody
    public List<QvOrder> findnoworder(int uid){
        System.out.println(uid);
        List<QvOrder> list=qorderService.findlastorder(uid);
        return list;
    }

}
