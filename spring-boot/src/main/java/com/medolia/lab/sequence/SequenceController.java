package com.medolia.lab.sequence;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lbli
 * @date 2021/9/20
 */
@RestController
@Slf4j
public class SequenceController {

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/sequence/demo")
    public String demo() throws Exception {
        SequenceGenerator generator = new SequenceGenerator() {{
            setPrefix("que-demo");
            setInitial(1);
            setSuffix(".json");
        }};
        String str = objectMapper.writeValueAsString(generator);
        log.info("demo:{} ", str);
        return str;
    }
}
