package org.example.sec01;

import java.util.concurrent.CountDownLatch;

public class InboundOutboundTaskDemo {
    private static final int MAX_PLATFORM = 10;

    public static void main(String[] args) throws InterruptedException {
        platFormThreadDemo3();
    }

    private static void platFormThreadDemo() {
        for (int i = 0; i < MAX_PLATFORM; i++) {
            int j = i;
            Thread thread = new Thread(() -> Task.ioIntensive(j));
            thread.start();
        }
    }

    private static void platFormThreadDemo2() {
//        Thread.Builder.OfPlatform builder=Thread.ofPlatform().name("vins",1);
        var builder = Thread.ofPlatform().name("vins", 1);
        for (int i = 0; i < MAX_PLATFORM; i++) {
            int j = i;
            Thread thread = builder.unstarted(() -> Task.ioIntensive(j));
            thread.start();
        }
    }

    private static void platFormThreadDemo3() throws InterruptedException {
        var latch = new CountDownLatch(MAX_PLATFORM);
        var builder = Thread.ofPlatform().name("daemon", 1);
        for (int i = 0; i < MAX_PLATFORM; i++) {
            int j = i;
            Thread thread = builder.unstarted(() -> {
                Task.ioIntensive(j);
                latch.countDown();
            });
            thread.start();
        }
        latch.await();
    }
}
