package com.medolia.demo.jvm;

/**
 * @author lbli@trip.com
 * @date 2021/8/16
 */
public class TryCatchTest {

    public static void main(String[] args) {
        try {
            System.out.println("try");
            throw new RuntimeException();
        } catch (Exception e) {
            System.out.println("catch");
            return;
        } finally {
            System.out.println("final");
        }
    }
}
