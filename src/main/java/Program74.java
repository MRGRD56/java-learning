import dev.b37.mgutils.concurrent.TaskInvoker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Program74 {
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    private static final Object monitor = new Object();

    public static void main(String[] args) {
        TaskInvoker<Void> taskInvoker = new TaskInvoker<>(executor);

        taskInvoker.submit(() -> {
            Thread.sleep(100);
            synchronized (monitor) {
                System.out.println("Waiting 1");
                monitor.wait();
                System.out.println("After wait 1");
            }
        });

        taskInvoker.submit(() -> {
            Thread.sleep(100);
            synchronized (monitor) {
                System.out.println("Waiting 2");
                monitor.wait();
                System.out.println("After wait 2");
            }
        });

        taskInvoker.submit(() -> {
            Thread.sleep(200);
            synchronized (monitor) {
                System.out.println("Notifying 1");
                monitor.notifyAll();
                System.out.println("After notify 1");
            }
        });

        taskInvoker.completeAllVoid();
        executor.shutdown();
    }
}
