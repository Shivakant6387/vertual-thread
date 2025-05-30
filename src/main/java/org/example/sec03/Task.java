package org.example.sec03;

import org.example.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Task {
    private static final Logger log = LoggerFactory.getLogger(org.example.sec02.Task.class);

    public static void execute(int i) {
        log.info("starting task {}", i);
        try {
            method1(i);
        } catch (Exception e) {
            log.error("error for {}", i, e);
        }
        log.info("ending task {}", i);
    }

    public static void method1(int i) {
        CommonUtil.sleep(Duration.ofMillis(300));
        method2(i);
    }

    public static void method2(int i) {
        CommonUtil.sleep(Duration.ofMillis(100));
        method3(i);
    }

    public static void method3(int i) {
        CommonUtil.sleep(Duration.ofMillis(500));
        if (i == 4) {
            throw new IllegalArgumentException("I can not be 4");
        }
    }
}
