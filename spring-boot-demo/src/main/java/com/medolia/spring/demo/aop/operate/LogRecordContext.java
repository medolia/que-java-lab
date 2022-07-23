package com.medolia.spring.demo.aop.operate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lbli
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogRecordContext {

    private String bizNumber;

    private String operator;

    private String category;

    private String content;

    private String condition;

}
