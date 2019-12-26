package com.shana.cinema.response;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author asus
 * @create 2019/10/19
 * @since 1.0.0
 */
public class ResponseResult {
    private String code;//错误代码    200  正确    500 错误
    private String message;
    private Map<String, Object> maps = new HashMap<>();

    public ResponseResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseResult() {
    }

    public ResponseResult add(String key, Object value) {
        this.getMaps().put(key, value);
        return this;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }
}
