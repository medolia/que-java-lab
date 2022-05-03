package com.medolia.demo.bulletScreen.validate;

import com.medolia.demo.bulletScreen.BulletScreen;

/**
 * @author lbli
 * @date 2022/5/3
 */
public interface ContentValidElement {

    ValidateResponse validate(BulletScreen content);

    int order();

    boolean isValid();
}
