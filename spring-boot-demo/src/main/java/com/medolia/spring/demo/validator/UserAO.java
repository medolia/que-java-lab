package com.medolia.spring.demo.validator;




import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author lbli@trip.com
 * @date 2021/8/10
 */
@Data
public class UserAO {

    @NotNull(groups = UpdateAction.class, message = "id不能为空")
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Integer age;

    @Valid
    private Phone phone;
}
