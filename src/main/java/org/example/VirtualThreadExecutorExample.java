package org.example;

// Java Program for Creating and executing
// Virtual thread with the
// Executors.newVirtualThreadPerTaskExecutor() Method

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class VirtualThreadExecutorExample {
    public static void main(String[] args) {
        try (ExecutorService myExecutor = Executors.newVirtualThreadPerTaskExecutor()) {
            // Submit a task that prints a message
            Future<?> future = myExecutor.submit(() -> System.out.println("Running thread"));
            // Wait for the task to complete
            future.get();
            System.out.println("Program Completed !!");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}