package com.medolia.spring.demo.logaop;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.slf4j.event.Level;
import org.springframework.stereotype.Component;

/**
 * @author lbli
 * @date 2022/7/23
 */
@Slf4j
@Component
public class DefaultLogEntityConsumer implements LogEntityConsumer{

    @Override
    public void consume(LogEntity logEntity) {
        MDC.put("title", StringUtils.defaultIfBlank(logEntity.getTitle(), StringUtils.EMPTY));
        logEntity.getTags().forEach(MDC::put);
        String content = logEntity.getContent();

        Level level = logEntity.getLevel();
        switch (level) {
            case INFO -> log.info(content);
            case WARN -> log.warn(content);
            case ERROR -> log.error(content);
        }
        MDC.clear();
    }
}
