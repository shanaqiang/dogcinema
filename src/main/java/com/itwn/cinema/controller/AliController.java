package com.itwn.cinema.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeCloseModel;
import com.alipay.api.domain.AlipayTradeFastpayRefundQueryModel;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.*;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.itwn.cinema.config.AlipayConfig;
import com.itwn.cinema.pojo.Pay;
import com.itwn.cinema.pojo.User;
import com.itwn.cinema.service.QorderService;
import com.itwn.cinema.service.UserService;
import com.itwn.cinema.utils.AliPayUtil;
import com.itwn.cinema.utils.OrderNumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2019/10/17
 * @since 1.0.0
 */
@Controller
public class AliController{
    private  static Pay pa=new Pay();
    @Autowired
    OrderNumUtils orderNumUtils;
    @Autowired
    UserService userService;
    @Autowired
    QorderService orderService;

    @ResponseBody
    @RequestMapping("/paySuccess")
    public String paySuccess() {
        return "支付成功";
    }

    @ResponseBody
    @RequestMapping("/payFail")
    public String payFail() {
        return "支付失败!";
    }

    /*** 调用支付*/
    /*支付 用form表单 访问此路径就好 */
    @RequestMapping(value = "/pay", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String goAlipay(String money, @RequestParam(value = "oid") String oid,
                           @RequestParam(value="pid",defaultValue = "1")int pid, HttpServletRequest request) throws IOException, AlipayApiException {
        User user= (User) request.getSession().getAttribute("user");
        AlipayClient alipayClient = AliPayUtil.alipayClient;
        String productCode = "FAST_INSTANT_TRADE_PAY";
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setProductCode(productCode);
        String orderNum = orderNumUtils.orderNumUtil();
        model.setOutTradeNo(orderNum);
        model.setSubject("开通会员");
        model.setTotalAmount(money);
        model.setBody("");
        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        model.setTimeoutExpress("15m");
        AlipayTradePagePayRequest pagePayRequest = new AlipayTradePagePayRequest();
        pagePayRequest.setReturnUrl(AlipayConfig.return_url);
        pagePayRequest.setNotifyUrl(AlipayConfig.notify_url);
        pagePayRequest.setBizModel(model);

        pa.setPid(pid);
        pa.setMoney(money);
        pa.setUid(5);
        pa.setOid(Integer.parseInt(oid));
        //请求
        String result = alipayClient.pageExecute(pagePayRequest).getBody();

       return result;
    }

    /*** 同步回调*/
    @RequestMapping("/return_url")
    public String return_url(HttpServletResponse response, HttpServletRequest request) throws IOException, AlipayApiException {
        /* log.info(">>>>>>>>支付成功, 进入同步通知接口...");*/
        System.out.println(">>>>>>>>支付成功, 进入同步通知接口...");
        boolean verifyResult = AliPayUtil.rsaCheckV1(request);
        ModelAndView mv = null;
        if (verifyResult) {
            //商户订单号
            String out_trade_no = AliPayUtil.getByte(request.getParameter("out_trade_no"));
            //支付宝交易号
            String trade_no = AliPayUtil.getByte(request.getParameter("trade_no"));
            /*log.info("商户订单号：{}，支付宝交易号，{}", out_trade_no, trade_no);*/
            System.out.println("商户订单号：{}，支付宝交易号，{}" + out_trade_no + trade_no);
            //mv = new ModelAndView("paySuccess");
            return "redirect:/index";
        } else {
            //mv = new ModelAndView("payFail");
            return "redirect:/index";
        }
        //return mv;
    }



    /*** 异步回调*/
    @ResponseBody
    @RequestMapping(value = "/notify_url", method = RequestMethod.POST)
    public String notify_url(HttpServletResponse response, HttpServletRequest request) throws IOException, AlipayApiException {
        System.out.println(">>>>>>>>支付成功, 进入异步通知接口...");
        // 一定要验签，防止黑客篡改参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder notifyBuild = new StringBuilder(">>>>>>>>>> alipay notify >>>>>>>>>>>>>>\n");
        parameterMap.forEach((key, value) -> notifyBuild.append(key + "=" + value[0] + "\n"));
        notifyBuild.append(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(notifyBuild.toString());
        boolean flag = AliPayUtil.rsaCheckV1(request);
        if (flag) {
            /**
             * TODO 需要严格按照如下描述校验通知数据的正确性
             *
             * 商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
             * 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
             * 同时需要校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
             *
             * 上述有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
             * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
             * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
             */

            //交易状态
            String tradeStatus = AliPayUtil.getByte(request.getParameter("trade_status"));
            // 商户订单号
            String out_trade_no = AliPayUtil.getByte(request.getParameter("out_trade_no"));
            System.out.println(out_trade_no);
            //支付宝交易号
            String trade_no = AliPayUtil.getByte(request.getParameter("trade_no"));
            //付款金额
            String total_amount = AliPayUtil.getByte(request.getParameter("total_amount"));

            //写sqlllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
            if(pa.getPid()==0){
                userService.updateVip(pa.getUid());
            }
            //付款成功就将订单状态变为1
            if(pa.getPid()==1){
                orderService.changeStatus1(pa.getOid());
            }

            System.out.println("okok");
            /*log.info("交易状态:{},商户订单号:{},支付宝交易号:{},付款金额:{}", tradeStatus, out_trade_no, trade_no, total_amount);*/
            System.out.println("交易状态:{},商户订单号:{},支付宝交易号:{},付款金额:{}" + tradeStatus + out_trade_no + trade_no + total_amount);
            // TRADE_FINISHED(表示交易已经成功结束，并不能再对该交易做后续操作);
            // TRADE_SUCCESS(表示交易已经成功结束，可以对该交易做后续操作，如：分润、退款等);
            if (tradeStatus.equals("TRADE_FINISHED")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，
                // 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                //如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
            } else if (tradeStatus.equals("TRADE_SUCCESS")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，
                // 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
            }
            return "success";
        }
        return "fail";
    }

    /**
     * 关闭交易
     *
     * @param orderNo
     * @return
     * @throws AlipayApiException
     */
    @PostMapping("/close")
    @ResponseBody
    public String close(String orderNo) throws AlipayApiException {
        AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();
        AlipayTradeCloseModel model = new AlipayTradeCloseModel();
        model.setOutTradeNo(orderNo);
        alipayRequest.setBizModel(model);
        AlipayTradeCloseResponse alipayResponse = AliPayUtil.alipayClient.execute(alipayRequest);
        System.out.println(alipayResponse.getBody());
        return alipayResponse.getBody();
    }

}
