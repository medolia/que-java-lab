package com.medolia.spring.demo.validator;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author lbli@trip.com
 * @date 2021/8/10
 */
@Data
public class Phone {

    @NotBlank
    private String operatorType;

    @NotBlank
    private String phoneNum;
}
