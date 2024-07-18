package task.evenOddThreads;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EvenOddThreads {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        final int N = 50; // Change N to the desired number of numbers to print
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        AtomicInteger number = new AtomicInteger(1);

        CompletableFuture<Void> oddFuture = CompletableFuture.runAsync(() -> {
            while (number.get() <= N) {
                lock.lock();
                try {
                    while (number.get() % 2 == 0) {
                        condition.await();
                    }
                    if (number.get() <= N) {
                        System.out.println(Thread.currentThread().getName() + " - " + number.getAndIncrement());
                    }
                    condition.signalAll();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        }, executor);

        CompletableFuture<Void> evenFuture = CompletableFuture.runAsync(() -> {
            while (number.get() <= N) {
                lock.lock();
                try {
                    while (number.get() % 2 != 0) {
                        condition.await();
                    }
                    if (number.get() <= N) {
                        System.out.println(Thread.currentThread().getName() + " - " + number.getAndIncrement());
                    }
                    condition.signalAll();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        }, executor);

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(oddFuture, evenFuture);

        try {
            combinedFuture.get(); // Wait for both threads to complete
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
        System.out.println(System.currentTimeMillis() - start + " ms");
    }
}