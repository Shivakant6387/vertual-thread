package org.example.sec04;

import org.example.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class CooperativeSchedulingDemo {
    private static final Logger log = LoggerFactory.getLogger(CooperativeSchedulingDemo.class);
    static {
        System.setProperty("jdk.VirtualThreadScheduler.parallelism","1");
        System.setProperty("jdk.VirtualThreadScheduler.maxPoolSize","1");
    }
    public static void main(String[] args) {
        var builder = Thread.ofVirtual();
        var t1 = builder.unstarted(() -> demo(1));
        var t2 = builder.unstarted(() -> demo(2));
        t1.start();
        t2.start();
        CommonUtil.sleep(Duration.ofSeconds(2));
    }

    private static void demo(int threadNumber) {
        log.info("thread-{} started ", threadNumber);
        for (int i = 0; i < 10; i++) {
            log.info("thread-{}is printing {}, Thread: {}", threadNumber, i, Thread.currentThread());
        }

        log.info("thread-{} ended ", threadNumber);
    }

}
