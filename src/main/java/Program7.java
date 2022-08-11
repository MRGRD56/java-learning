import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class Program7 {
    private Program7() { }

    private static final Object locker = new Object();

    private static final ExecutorService executor = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        for (var i = 0; i < 5; i++) {
            final var iVal = i;
            executor.submit(() -> {
                try {
                    Thread.sleep(1000 + 50 * iVal);
                    System.out.println("HELLO i=" + iVal);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    if (iVal == 4) {
                        synchronized (locker) {
                            locker.notifyAll();
                        }
                    }
                }
            });
        }

        synchronized (locker) {
            try {
                locker.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("FINISHED!!!");
        }
    }
}
