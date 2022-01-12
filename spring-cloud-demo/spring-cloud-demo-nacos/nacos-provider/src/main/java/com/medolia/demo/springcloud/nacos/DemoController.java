package com.medolia.demo.springcloud.nacos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
@RestController
@RequestMapping("/nacos")
public class DemoController {

    @GetMapping("/test/{id}")
    public String demo(@PathVariable("id") Integer id) {
        return "id in path: " + id;
    }

}
