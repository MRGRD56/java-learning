import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockProgram {
    private static final Lock lock1 = new ReentrantLock();
    private static final Lock lock2 = new ReentrantLock();

    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        executor.submit(() -> {
            lock1.lock();
            System.out.println("Thread 1 - lock1");
            try {
                sleep(100);
                lock2.lock();
                System.out.println("Thread 1 - lock2");
                try {
                    sleep(100);
                    System.out.println("Thread 1 - finish");
                } finally {
                    lock2.unlock();
                }
            } finally {
                lock1.unlock();
            }
        });
        executor.submit(() -> {
            lock2.lock();
            System.out.println("Thread 2 - lock2");
            try {
                sleep(100);
                lock1.lock();
                System.out.println("Thread 2 - lock1");
                try {
                    sleep(100);
                    System.out.println("Thread 2 - finish");
                } finally {
                    lock1.unlock();
                }
            } finally {
                lock2.unlock();
            }
        });

        executor.shutdown();
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
