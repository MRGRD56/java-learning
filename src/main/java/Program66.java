import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Program66 {
    private static final ExecutorService executor = Executors.newFixedThreadPool(2);
    public static void main(String[] args) {
        List<Future<Double>> futures = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            futures.add(executor.submit(() -> {
                Thread.sleep(300);
                return Math.random();
            }));

            futures.add(executor.submit(Executors.callable(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, 100.0)));
        }

        for (Future<Double> future : futures) {
            try {
                boolean isDone = future.isDone();
                System.out.println(future.get() + " " + (isDone ? "DONE" : "IN_PROGRESS"));
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        executor.shutdown();
    }
}
