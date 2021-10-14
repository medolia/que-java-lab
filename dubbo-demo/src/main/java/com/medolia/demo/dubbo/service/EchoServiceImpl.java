package com.medolia.demo.dubbo.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lbli@trip.com
 * @date 2021/9/16
 */
@DubboService
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String message) {
        String now = new SimpleDateFormat("HH:mm:ss").format(new Date());
        System.out.printf("[ %s ] Hello~ %s, request from consumer: %s%n", now, message,
                RpcContext.getClientAttachment().getLocalAddressString());
        return message;
    }
}
