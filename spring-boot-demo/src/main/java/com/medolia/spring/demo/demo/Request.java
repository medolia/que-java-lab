package com.medolia.spring.demo.demo;

/**
 * @author lbli
 * @date 2022/7/23
 */
public class Request {

    private String uuid;
    private String data;
    private DTO dto;
    public Request(String uuid, String data) {
        this.uuid = uuid;
        this.data = data;
    }

    public Request(String uuid, String data, DTO dto) {
        this.uuid = uuid;
        this.data = data;
        this.dto = dto;
    }

    public static Request getInstance(String uuid, String data) {
        return new Request(uuid, data);
    }

    public static Request getInstance(String uuid, String data, String orderNumber) {
        return new Request(uuid, data, new DTO() {{
            setOrderNumber(orderNumber);
        }});
    }

    public DTO getDto() {
        return dto;
    }

    public void setDto(DTO dto) {
        this.dto = dto;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private static class DTO {
        private String orderNumber;

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }
    }
}
