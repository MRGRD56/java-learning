import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class CustomFixedExecutor_3 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CustomFixedExecutor_3 executor = new CustomFixedExecutor_3(2);

        List<Future<Integer>> futures = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < 10; i++) {
            int value = i;
            Future<Integer> future = executor.submit(() -> {
                System.out.println("Started " + value);

                try {
                    Thread.sleep(2000);
                    return value;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    System.out.println("Finished " + value);
                }
            });
            futures.add(future);
        }


        for (Future<Integer> future : futures) {
            future.get();
//            System.out.println("Completed " + future.get());
        }

        System.out.println(futures);
    }

    private final int threadsCount;
    private final AtomicBoolean isTerminated = new AtomicBoolean(false);
    private final Semaphore semaphore;

    public CustomFixedExecutor_3(int threadsCount) {
        this.threadsCount = threadsCount;
        this.semaphore = new Semaphore(threadsCount, true);
    }

    public <T> Future<T> submit(Supplier<T> task) {
        CompletableFuture<T> future = new CompletableFuture<>();

        CompletableFuture.runAsync(() -> {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                semaphore.release();
                return;
            }

            try {
                future.complete(task.get());
            } catch (Throwable e) {
                future.completeExceptionally(e);
            }

            semaphore.release();
        });

        return future;
    }

    public void shutdown() {
        isTerminated.set(true);
    }
}
