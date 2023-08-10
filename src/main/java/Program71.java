import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Program71 {

    private static class AtomicEvenGenerator extends IntGenerator {
        private final AtomicInteger currentEvenValue = new AtomicInteger(0);
        public int next() {
            return currentEvenValue.addAndGet(2);
        }
        public static void main(String[] args) {
            EvenChecker.test(new AtomicEvenGenerator());
        }
    }

    private static class SynchronizedEvenGenerator extends IntGenerator {
        private int currentEvenValue = 0;
        public synchronized int next() {
            ++currentEvenValue; // Опасная точка!
            Thread.yield();
            ++currentEvenValue;
            return currentEvenValue;
        }
        public static void main(String[] args) {
            EvenChecker.test(new SynchronizedEvenGenerator());
        }
    }

    private static class LockedEvenGenerator extends IntGenerator {
        private final Lock lock = new ReentrantLock();

        private int currentEvenValue = 0;
        public synchronized int next() {
            lock.lock();
            try {
                ++currentEvenValue; // Опасная точка!
                Thread.yield();
                ++currentEvenValue;
                return currentEvenValue;
            } finally {
                lock.unlock();
            }
        }
        public static void main(String[] args) {
            EvenChecker.test(new LockedEvenGenerator());
        }
    }

    private static class EvenChecker implements Runnable {
        private final IntGenerator generator;
        private final int id;

        public EvenChecker(IntGenerator g, int ident) {
            generator = g;
            id = ident;
        }

        public void run() {
            while (!generator.isCanceled()) {
                int val = generator.next();
                if (val % 2 != 0) {
                    System.out.println(val + " not even!");
                    generator.cancel(); // Отмена всех EvenChecker
                }
            }
        }

        // Тестирование произвольного типа IntGenerator:
        public static void test(IntGenerator gp, int count) {
            System.out.println("Press Control-C to exit");
            ExecutorService exec = Executors.newCachedThreadPool();
            for (int i = 0; i < count; i++) {
                exec.execute(new EvenChecker(gp, i));
            }
            exec.shutdown();
        }

        // Значение по умолчанию для count:
        public static void test(IntGenerator gp) {
            test(gp, 10);
        }
    }

    private static abstract class IntGenerator {
        private volatile boolean canceled = false;

        public abstract int next();

        // Allow this to be canceled:
        public void cancel() { canceled = true; }

        public boolean isCanceled() { return canceled; }
    }
}
