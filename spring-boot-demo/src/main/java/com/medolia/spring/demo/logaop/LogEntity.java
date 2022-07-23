package com.medolia.spring.demo.logaop;

import lombok.Builder;
import lombok.Data;
import org.slf4j.event.Level;

import java.util.Map;

/**
 * @author lbli
 * @date 2022/7/23
 */
@Data
@Builder
public class LogEntity {

    private Level level;
    private String title;
    private String content;
    private Map<String, String> tags;

}
