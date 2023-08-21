import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Program84 {
    public static void main(String[] args) {
        while (true) {
            try {
                new Object() {
                    private static final ReentrantLock lock = new ReentrantLock();
                    private static final Condition condition = lock.newCondition();
                    private final byte[] bytes = new byte[10 * 1024];

                    @Override
                    protected void finalize() throws Throwable {
                        System.out.println("Finalize called " + Thread.currentThread().getName() + " lock: " + lock);
                        lock.lock();
                        try {
                            condition.await();
                        } finally {
                            lock.unlock();
                        }
                    }
                };
            } catch (OutOfMemoryError e) {
                e.printStackTrace(System.err);
            }
        }
    }
}
