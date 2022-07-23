package com.medolia.spring.demo.demo;

import com.medolia.spring.demo.logaop.NeedAddTag;
import com.medolia.spring.demo.logaop.NeedLog;
import com.medolia.spring.demo.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lbli@trip.com
 * @date 2021/8/26
 */
@Component
@Slf4j
public class MicroServiceHandler {

    @NeedLog(resTags = "code")
    public Response request(
            @NeedAddTag({"uuid", "dto.orderNumber"}) Request request1,
            @NeedAddTag({"data", "notag"}) Request request2) {
        log.info("start to handle request: {}", JsonUtils.serialize(request1) + JsonUtils.serialize(request2));
        return new Response(1, "response data");
    }

}
