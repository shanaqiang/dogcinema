package com.shana.cinema.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.Random;
@Component
public class Mailutil {

    public String mailutil(String mail,HttpServletRequest req){
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.qq.com");
        email.setCharset("utf-8");
        try {
            String num = "" + new Random().nextInt(1000000);
            Date createtime = new Date();
            req.getSession().setAttribute("createtime",createtime);
            req.getSession().setAttribute("codemail",num);
            email.addTo(mail);
            email.setFrom("2433296112@qq.com", "aa");
            email.setAuthentication("2433296112@qq.com", "ffirofxrwwvwdigf");
            email.setSubject("helloworld");//设置发送主题
            email.setMsg("您的验证码码:" + num);//设置发送内容
            email.send();//进行发送
        } catch (EmailException e) {
            e.printStackTrace();
        }
        return "验证码已发送请在1分钟内送输入";
    }
}
