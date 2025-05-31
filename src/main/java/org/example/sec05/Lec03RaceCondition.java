package org.example.sec05;

import org.example.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Lec03RaceCondition {
    private static final Logger log = LoggerFactory.getLogger(Lec03RaceCondition.class);
    private static final List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        Runnable runnable = () -> log.info("*** Test Message ***");
//        demo(Thread.ofPlatform());
//        Thread.ofPlatform().start(runnable);
        demo(Thread.ofVirtual());
        Thread.ofVirtual().start(runnable);
        CommonUtil.sleep(Duration.ofSeconds(15));
    }

    private static void demo(Thread.Builder builder) {
        for (int i = 0; i < 50; i++) {
            builder.start(() -> {
                log.info("Task started. {}", Thread.currentThread());
                for (int j = 0; j < 200; j++) {
                    ioTask();
                }
                log.info("Task Ended. {}", Thread.currentThread());
            });
        }
    }

    private static synchronized void ioTask() {
        list.add(1);
        CommonUtil.sleep(Duration.ofSeconds(10));
    }
}
