package com.medolia.demo.jvm;

/**
 * <p> This short code example is a perfect demo of why you shouldn’t try to catch a StackOverflowError.
 * <p> It simply cannot be guaranteed that there is enough stack available for application code to do anything reasonable - not even logging it.
 *
 *
 * @author lbli@trip.com
 * @since 0.0.1
 */
public class StackOverflowErrorCatcher {

    static Object m1(boolean var0) {
        throw new NullPointerException();
    }

    static boolean m2(boolean var0) {
        boolean var1 = false;

        try {
            var1 = m2(var0);
        } catch (StackOverflowError e) {
            return true;
        }
        System.out.println("This is the only diff");
        if (var1) {
            try {
                m1(var0);
            } catch (StackOverflowError e) {
            }
        }
        return false;
    }

    public static void main(String[] var0) throws Exception {
        try {
            m2(true);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.in.read();
    }
}
