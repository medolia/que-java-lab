package com.medolia.demo.dubbo.client;

import com.medolia.demo.dubbo.service.EchoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * @author lbli@trip.com
 * @date 2021/9/16
 */
@Component
public class EchoConsumer {
    @DubboReference
    private EchoService echoService;

    public String echo(String name) {
        return echoService.echo(name);
    }
}
