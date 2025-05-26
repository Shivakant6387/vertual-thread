package org.example;

public class VirtualThreadExample2 {
    public static void main(String[] args) {
        // Creating 5 virtual threads
        for (int i = 1; i <= 5; i++) {
            int taskNumber = i;
            Thread.startVirtualThread(() -> {
                System.out.println("Running task " + taskNumber + " in: " + Thread.currentThread());
                try {
                    Thread.sleep(1000); // Simulate some blocking I/O
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Task " + taskNumber + " completed");
            });
        }

        // Wait for some time to allow all threads to complete
        try {
            Thread.sleep(2000); // Main thread waits for others
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
