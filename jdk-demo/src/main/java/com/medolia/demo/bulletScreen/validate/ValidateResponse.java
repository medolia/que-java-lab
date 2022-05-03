package com.medolia.demo.bulletScreen.validate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lbli
 * @date 2022/5/3
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidateResponse {

    private boolean validateSuccess;

    private String desc;

    public static ValidateResponse success() {
        return builder().validateSuccess(true).desc("success").build();
    }

    public static ValidateResponse fail(String failReason) {
        return builder().validateSuccess(false).desc(failReason).build();
    }

}
