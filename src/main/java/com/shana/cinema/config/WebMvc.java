package com.shana.cinema.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2019/10/17
 * @since 1.0.0
 */
@Configuration
public class WebMvc implements WebMvcConfigurer {

    //郭力到此一游
    //test svn
    //test123456

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("front/index");
        registry.addViewController("/films").setViewName("front/films");
        registry.addViewController("/film/1").setViewName("front/filmdetails");
        registry.addViewController("/cinemas").setViewName("front/cinemas");
        registry.addViewController("/cinema/1").setViewName("front/cinemadetails");
        registry.addViewController("/board").setViewName("front/board");
        registry.addViewController("/login").setViewName("front/login");
        registry.addViewController("/register").setViewName("front/register");
        registry.addViewController("/myinfopage").setViewName("front/myinfopage");
        registry.addViewController("/mylikepage").setViewName("front/mylikepage");
        registry.addViewController("/myorderpage").setViewName("front/myorderpage");
        registry.addViewController("/showAllThreater").setViewName("background/showAllThreater");
        registry.addViewController("/map").setViewName("background/map");
        registry.addViewController("/email_login").setViewName("front/email_login");
        registry.addViewController("/qregister").setViewName("front/register");
        registry.addViewController("/123").setViewName("background/advert123");
        registry.addViewController("/index").setViewName("index");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/view/**")
                .excludePathPatterns("/asserts/**","/background/**","/filming/**","/front/**","/view/loginview")
                .excludePathPatterns("/backoperate/getVerifiCode","/backoperate/login");
    }
}
