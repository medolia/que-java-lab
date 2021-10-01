package com.medolia.lab.sequence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lbli
 * @date 2021/9/14
 */
@Configuration
public class SequenceGeneratorConfiguration {

    @Bean
    public SequenceGenerator sequenceGenerator() {
        return new SequenceGenerator() {{
            setPrefix("30");
            setSuffix("A");
            setInitial(10000);
        }};
    }
}
