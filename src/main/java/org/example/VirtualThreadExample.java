package org.example;


public class VirtualThreadExample {
    public static void main(String[] args) {
        try {
            Thread.Builder builder = Thread.ofVirtual().name("GFG Thread");
            Runnable task = () -> {
                System.out.println("Running thread");
            };
            Thread t = builder.start(task);
            System.out.println("Thread t name: " + t.getName());
            Thread.sleep(1000);
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
