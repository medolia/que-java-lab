package com.medolia.spring.demo.logaop;

import com.medolia.spring.demo.demo.MicroServiceHandler;
import com.medolia.spring.demo.demo.Request;
import com.medolia.spring.demo.demo.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

/**
 * @author lbli
 * @date 2022/5/1
 */
@SpringBootTest
class LogAspectTest {

    @Autowired
    private MicroServiceHandler microServiceHandler;

    @Test
    void test() {
        Response response = microServiceHandler.request(
                Request.getInstance(UUID.randomUUID().toString(), "xenoblade 3 ~", "198892194"),
                new Request(
                        UUID.randomUUID().toString(),
                        "90% discount for diablo 3"));
        System.out.println(response);
    }
}