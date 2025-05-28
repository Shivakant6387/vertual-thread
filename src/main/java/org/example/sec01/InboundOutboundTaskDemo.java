package org.example.sec01;

public class InboundOutboundTaskDemo {
    private static final int MAX_PLATFORM = 10;

    public static void main(String[] args) {
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
    private static void platFormThreadDemo3() {
//        Thread.Builder.OfPlatform builder=Thread.ofPlatform().name("vins",1);
        var builder = Thread.ofPlatform().name("daemon", 1);
        for (int i = 0; i < MAX_PLATFORM; i++) {
            int j = i;
            Thread thread = builder.unstarted(() -> Task.ioIntensive(j));
            thread.start();
        }
    }
}
