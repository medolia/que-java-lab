package com.medolia.spring.demo.demo;

/**
 * @author lbli
 * @date 2022/7/23
 */
public class Response {

    private Integer code;
    private String data;

    public Response(Integer code, String data) {
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
