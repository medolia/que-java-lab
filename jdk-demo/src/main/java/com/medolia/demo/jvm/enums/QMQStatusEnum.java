package com.medolia.demo.jvm.enums;

/**
 * @author lb@li@trip.com
 * @date 2021/7/12
 */
public enum QMQStatusEnum {
    /**
     *
     */
    None(0),
    Processing(1),
    Done(2),
    Settled(3),
    Unqualified(4),
    Forbidden(5),
    Unfinished(6),
    Unsettled(7),
    Finished(8),
    Expired(9);

    private final Integer statusCode;

    QMQStatusEnum(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public static void main(String[] args) {
        String status = QMQStatusEnum.Done.name();
        int statusCode = QMQStatusEnum.Done.statusCode;
        System.out.println("status " + status + " " + "code " + statusCode);
    }
}


