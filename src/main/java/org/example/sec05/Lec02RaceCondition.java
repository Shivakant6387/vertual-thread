package org.example.sec05;

import org.example.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Lec02RaceCondition {
    private static final Logger log = LoggerFactory.getLogger(Lec01RaceCondition.class);
    private static final List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
//        demo(Thread.ofPlatform());
        demo(Thread.ofVirtual());
        CommonUtil.sleep(Duration.ofSeconds(2));
        log.info("List size: {}", list.size());
    }

    private static void demo(Thread.Builder builder) {
        for (int i = 0; i < 50; i++) {
            builder.start(() -> {
                log.info("Task started. {}", Thread.currentThread());
                for (int j = 0; j < 200; j++) {
                    inMemoryTask();
                }
                log.info("Task Ended. {}", Thread.currentThread());
            });
        }
    }

    private static synchronized void inMemoryTask() {
        list.add(1);
    }
}
