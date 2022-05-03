package com.medolia.demo.bulletScreen.validate;

import com.medolia.demo.bulletScreen.BulletScreen;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @author lbli
 * @date 2022/5/3
 */
@Slf4j
public class ContentValidateChain {

    private static final Object lock = new Object();
    private final List<ContentValidElement> contentValidElementList;

    public ContentValidateChain() {
        this(Lists.newArrayList());
    }

    public ContentValidateChain(List<ContentValidElement> contentValidElementList) {
        this.contentValidElementList = Objects.requireNonNull(contentValidElementList);
    }

    public void addElement(ContentValidElement element) {
        synchronized (lock) {
            this.contentValidElementList.add(element);
            contentValidElementList.sort(
                    Comparator.comparing(ContentValidElement::isValid)
                            .thenComparing(ContentValidElement::order));
        }
    }

    public ValidateResponse fireChain(BulletScreen content) {

        for (ContentValidElement element : this.contentValidElementList) {
            if (!element.isValid()) {
                continue;
            }

            ValidateResponse response = element.validate(content);
            if (!response.isValidateSuccess()) {
                log.warn("content validate fail, element: {}, reason: {}",
                        element.getClass().getSimpleName(), response.getDesc());
                return response;
            }
        }

        return ValidateResponse.success();
    }

}
