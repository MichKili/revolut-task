package task.evenOddThreads.full;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class EvenOddThreadsFull {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        final State state = new State(PrinterType.ODD);
        final Printer oddPrinter = new Printer(1, 2, state, PrinterType.ODD, PrinterType.EVEN, 50);
        final Printer evenPrinter = new Printer(2, 2, state, PrinterType.EVEN, PrinterType.ODD, 50);

        final Thread oddThread = new Thread(oddPrinter);
        final Thread evenThread = new Thread(evenPrinter);

        oddThread.start();
        evenThread.start();

        try {
            oddThread.join();
            evenThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(System.currentTimeMillis() - start + " ms");
    }
}
