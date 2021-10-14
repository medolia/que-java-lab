package com.medolia.spring.demo.validator;

import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * @author lbli@trip.com
 * @date 2021/8/10
 */
@RestControllerAdvice(basePackages = {"com.medolia.javaLab.com.medolia.spring.demo.validator"})
public class GlobalExceptionHandler {


    @ExceptionHandler(value = {Throwable.class})
    Result handleException(Throwable e, HttpServletRequest request) {

        Result result = new Result();

        if (e instanceof MissingServletRequestParameterException) {
            result.setMessage("缺少参数");
        } else if (e instanceof ConstraintViolationException) {
            result.setMessage("单参数校验异常");
        }  else if (e instanceof MethodArgumentNotValidException) {
            result.setMessage("post 请求对象校验失败");
        } else if (e instanceof BindException) {
            result.setMessage("对象参数校验异常");
        } else {
            result.setMessage("");
        }


        return result;
    }
}
