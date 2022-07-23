package com.medolia.arch.game.yoka;

import org.apache.commons.lang3.StringUtils;

/**
 * @author lbli
 * @date 2022/6/26
 */
public interface Card {
    /**
     * 点数
     */
    int cardPoint();

    long id();

    /**
     * 花色
     */
    Suit suit();

    /**
     * 牌类
     */
    CardType type();

    /**
     * 名称
     */
    String name();

    /**
     * 图片链接
     */
    default String img() {
        return StringUtils.EMPTY;
    };

}
