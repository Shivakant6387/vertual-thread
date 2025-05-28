package org.example.sec02;

import org.example.util.CommonUtil;

import java.time.Duration;

public class StackTraceDemo {
    public static void main(String[] args) {
        demoThread(Thread.ofVirtual().name("virtual-", 1));
        CommonUtil.sleep(Duration.ofSeconds(2));
    }

    private static void demoThread(Thread.Builder builder) {
        for (int i = 1; i <= 20; i++) {
            int j = i;
            builder.start(() -> Task.execute(j));
        }
    }
}
