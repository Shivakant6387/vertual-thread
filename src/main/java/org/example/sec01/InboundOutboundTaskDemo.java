package org.example.sec01;

import java.util.concurrent.CountDownLatch;

public class InboundOutboundTaskDemo {
    private static final int MAX_PLATFORM = 10;
    private static final int MAX_VIRTUAL = 20;

    public static void main(String[] args) throws InterruptedException {
//        platFormThreadDemo3();
        virtualThreadDemo();
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

    private static void virtualThreadDemo() throws InterruptedException {
        var latch = new CountDownLatch(MAX_VIRTUAL);
        var builder = Thread.ofVirtual().name("virtual-", 1);
        for (int i = 0; i < MAX_VIRTUAL; i++) {
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
