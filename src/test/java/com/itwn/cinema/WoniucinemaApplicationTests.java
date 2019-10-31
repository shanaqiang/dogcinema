package com.itwn.cinema;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WoniucinemaApplicationTests {

    @Test
    public void contextLoads() {
        int a=Integer.MAX_VALUE;
        int b=Integer.MAX_VALUE;
        int sum=a+b;
        System.out.println("a="+a+",b="+b+",sum="+sum);
    }

}
