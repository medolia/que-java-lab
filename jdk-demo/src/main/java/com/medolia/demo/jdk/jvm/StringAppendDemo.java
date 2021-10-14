package com.medolia.demo.jdk.jvm;

/**
 * @author lbli@trip.com
 * @date 2021/8/17
 */
public class StringAppendDemo {
    public static void main(String[] args) {
        String s = "";
        for (int i = 0; i < 15; i++) {
            s += "i";
        }
        System.out.println(s);

        String[] strList = new String[]{"que", "medolia", "sakito", "repar"};
        for (String str : strList) {
            s += str;
            System.out.println(str);
        }
    }
}
