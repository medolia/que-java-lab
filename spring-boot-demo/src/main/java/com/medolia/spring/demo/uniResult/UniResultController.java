package com.medolia.spring.demo.uniResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lbli
 * @date 2021/10/1
 */
@RestController
public class UniResultController {

    @GetMapping(value = "/demo")
    public String demo() {
        return "hello, this is demo data";
    }

    @GetMapping(value = "/demo/1")
    public Integer demo1() {
        return 114;
    }

    @GetMapping(value = "/error")
    public void error() {
        throw new RuntimeException("demo error");
    }
}
