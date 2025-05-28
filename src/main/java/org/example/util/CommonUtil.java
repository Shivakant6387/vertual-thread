package org.example.util;

import java.time.Duration;

public class CommonUtil {
    public static void sleep(Duration duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
