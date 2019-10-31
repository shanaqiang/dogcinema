package com.itwn.cinema.utils;

import java.util.Random;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author xiana
 * @create 2019/9/28
 * @since 1.0.0
 */
//随机生成验证码
public class CodeMaker {
    //生成验证码的位数
    private static int codeCount = 4;
    private static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1',
            '2', '3', '4', '5', '6', '7', '8', '9' };

    public static String getRandom(){
        Random random = new Random();
        StringBuffer randomCode = new StringBuffer();
        for (int i = 0; i < codeCount; i++) {
            String code = String.valueOf(codeSequence[random.nextInt(36)]);
            randomCode.append(code);
        }
        return randomCode.toString();
    }
}

