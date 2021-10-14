package com.medolia.spring.demo.refactor;

import com.medolia.spring.demo.refactor.handlers.OrderHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lbli@trip.com
 * @date 2021/8/19
 */
@Service
@Slf4j
public class OrderService implements InitializingBean {

    private Map<OrderHandlerType, OrderHandler> orderHandlerMap;

    @Autowired
    public void setOrderHandlerMap(List<OrderHandler> orderHandlerList) {
        orderHandlerMap = orderHandlerList.stream().collect(
                Collectors.toMap(
                        handler -> AnnotationUtils.findAnnotation(handler.getClass(), OrderHandlerType.class),
                        v -> v,
                        (v1, v2) -> v1));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("map: {}", new ObjectMapper().writeValueAsString(orderHandlerMap));
    }

    public void processOrderNew(Order order) {
        // before

        OrderHandlerType orderHandlerType = new OrderHandlerTypeImpl(order.getSource(), order.getPayMethod());
        OrderHandler orderHandler = orderHandlerMap.get(orderHandlerType);
        orderHandler.handle(order);

        // after
    }

    public void processOrderOld(Order order) {
        if(order.getSource().equals("pc")){
            // 处理pc端订单的逻辑
        }else if(order.getSource().equals("mobile")){
            // 处理移动端订单的逻辑
        }else {
            // 其他逻辑
        }
    }
}
