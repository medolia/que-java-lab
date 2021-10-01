package com.medolia.lab.uniResult;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lbli
 * @date 2021/10/1
 */
@Slf4j
@RestControllerAdvice
public class UniExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> handle(Exception e) {
        log.warn("发生异常: {}", e.getMessage(), e);
        return ResultData.fail(ReturnCode.RC500.getCode(), e.getMessage());
    }
}
