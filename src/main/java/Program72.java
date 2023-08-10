import dev.b37.mgutils.concurrent.TaskInvoker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Program72 {
    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    private static final Object monitor = new Object();

    private static final Lock unfairLock = new ReentrantLock(false);
    private static final Lock fairLock = new ReentrantLock(true);

    public static void main(String[] args) {
        TaskInvoker<Void> taskInvoker = new TaskInvoker<>(executor);

        for (int i = 0; i < 10; i++) {
            int number = i;

            taskInvoker.submit(() -> {
                try {
                    Thread.sleep(50 * number);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (unfairLock.tryLock()) {
                    try {
                        System.out.println("[Try-UnfairLock] Thread " + number);

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } finally {
                        unfairLock.unlock();
                    }
                }
            });
        }

        taskInvoker.completeAllVoid();

        for (int i = 0; i < 10; i++) {
            int number = i;

            taskInvoker.submit(() -> {
                synchronized (monitor) {
                    System.out.println("[Monitor] Thread " + number);

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

        taskInvoker.completeAllVoid();

        for (int i = 0; i < 10; i++) {
            int number = i;

            taskInvoker.submit(() -> {
                fairLock.lock();
                try {
                    System.out.println("[FairLock] Thread " + number);

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } finally {
                    fairLock.unlock();
                }
            });
        }

        taskInvoker.completeAllVoid();

        executor.shutdown();
    }
}
