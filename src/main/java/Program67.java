import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Program67 {
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        executor.execute(new SimplePriorities(Thread.MIN_PRIORITY));
        executor.execute(new SimplePriorities(Thread.MAX_PRIORITY));
        executor.shutdown();
    }

    public static class SimplePriorities implements Runnable {
        private final int priority;

        private volatile double counter;

        public SimplePriorities(int priority) {
            this.priority = priority;
        }

        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            thread.setPriority(priority);

            for (int i = 0; i < 5; i++) {
                for (int j = 1; j < 1_000_000; j++) {
                    counter += (Math.PI + Math.E) / (double) j;

                    if (j % 1000 == 0) {
                        Thread.yield();
                    }
                }

                System.out.printf("%s -> %d%n", thread.getName(), i);
            }
        }
    }
}
