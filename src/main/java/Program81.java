import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Program81 {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

        ScheduledFuture<?> task =  scheduledExecutor.scheduleAtFixedRate(() -> {
            System.out.println("Task has started");

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Task has been interrupted");
                return;
            }

            System.out.println("Task has finished");
        }, 1000, 1500, TimeUnit.MILLISECONDS);

        try {
            Thread.sleep(4300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        task.cancel(true);

        scheduledExecutor.shutdown();
    }
}
