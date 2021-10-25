package com.medolia.demo.jvm.enums;

/**
 * @author lbli@trip.com
 * @date 2021/8/11
 */
public enum PayrollDay {
    /**
     * 一周
     */
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);

    private final PayType payType;

    PayrollDay(PayType payType) {
        this.payType = payType;
    }

    PayrollDay() {
        this(PayType.WEEKDAY);
    }

    // strategy
    private enum PayType {
        WEEKDAY, WEEKEND;


    }

}

