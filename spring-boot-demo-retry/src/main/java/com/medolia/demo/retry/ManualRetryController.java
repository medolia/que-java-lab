package com.medolia.demo.retry;

import com.medolia.demo.retry.manual.DefaultRetryListener;
import com.medolia.demo.retry.manual.FastRetryStrategy;
import com.medolia.demo.retry.manual.Retry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
@RestController
public class ManualRetryController {
    public static int count = 0;

    @Retry(maxAttempts = 5, delay = 100, value = {ArithmeticException.class},
            strategy = FastRetryStrategy.class, listener = DefaultRetryListener.class)
    @GetMapping(value = "/do-test")
    public String doTest(int code) {
        count++;
        System.out.println("code is :" + code + " result is :" + count % 3 + " count is :" + count);
        if (code == 1) {
            System.out.println("--this is a test");
        } else {
            if (count % 5 != 0) {
                System.out.println(4 / 0);
            }
        }
        return "success";
    }
}
