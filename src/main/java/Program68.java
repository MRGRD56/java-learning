import dev.b37.mgutils.concurrent.Lazy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.*;

public class Program68 {
    private static final ExecutorService executor = new DaemonThreadPoolExecutor();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            int threadNumber = i;

            executor.submit(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("FINISHED " + threadNumber);
            });
        }

        executor.shutdown();

        Thread.sleep(500);

        System.out.println("FINISHED MAIN");
    }

    private static class DaemonThreadPoolExecutor extends ThreadPoolExecutor {
        public DaemonThreadPoolExecutor() {
            super(1, 1, 0L,
                    TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(),
                    DaemonThreadFactory.getInstance());
        }
    }

    private static class DaemonThreadFactory implements ThreadFactory {
        private static final Lazy<DaemonThreadFactory> instance = new Lazy<>(DaemonThreadFactory::new);

        private DaemonThreadFactory() { }

        public static DaemonThreadFactory getInstance() {
            return instance.get();
        }

        @Override
        public Thread newThread(@NotNull Runnable r) {
            return new DaemonThread(r);
        }
    }

    private static class DaemonThread extends Thread {
        public DaemonThread() {
            this.setDaemon(true);
        }

        public DaemonThread(Runnable target) {
            super(target);
            this.setDaemon(true);
        }

        public DaemonThread(@Nullable ThreadGroup group, Runnable target) {
            super(group, target);
            this.setDaemon(true);
        }

        public DaemonThread(@NotNull String name) {
            super(name);
            this.setDaemon(true);
        }

        public DaemonThread(@Nullable ThreadGroup group, @NotNull String name) {
            super(group, name);
            this.setDaemon(true);
        }

        public DaemonThread(Runnable target, String name) {
            super(target, name);
            this.setDaemon(true);
        }

        public DaemonThread(@Nullable ThreadGroup group, Runnable target, @NotNull String name) {
            super(group, target, name);
            this.setDaemon(true);
        }

        public DaemonThread(@Nullable ThreadGroup group, Runnable target, @NotNull String name, long stackSize) {
            super(group, target, name, stackSize);
            this.setDaemon(true);
        }

        public DaemonThread(ThreadGroup group, Runnable target, String name, long stackSize, boolean inheritThreadLocals) {
            super(group, target, name, stackSize, inheritThreadLocals);
            this.setDaemon(true);
        }
    }
}
