import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class CustomFixedExecutor {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CustomFixedExecutor executor = new CustomFixedExecutor(2);

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
            System.out.println("Completed " + future.get());
        }

        System.out.println(futures);
    }

    private final BlockingQueue<ExecutorTask<?>> tasksQueue = new LinkedBlockingQueue<>();
    private final int threadsCount;
    private final List<ExecutorThread> threads = new ArrayList<>();
    private final AtomicBoolean isTerminated = new AtomicBoolean(false);
    private final AtomicInteger activeTasks = new AtomicInteger(0);

    public CustomFixedExecutor(int threadsCount) {
        this.threadsCount = threadsCount;
        for (int i = 0; i < threadsCount; i++) {
            ExecutorThread thread = new ExecutorThread();
            this.threads.add(thread);
            thread.start();
        }
    }

    public <T> Future<T> submit(Supplier<T> task) {
        CompletableFuture<T> future = new CompletableFuture<>();
        tasksQueue.add(new ExecutorTask<T>(task, future));
        return future;
    }

    public void awaitCompletion() throws InterruptedException {
        if (activeTasks.get() <= 0) {
            return;
        }

        synchronized (activeTasks) {
            while (activeTasks.get() > 0) {
                activeTasks.wait();
            }
        }
    }

    public void shutdown() {
        isTerminated.set(true);
    }

    private class ExecutorThread extends Thread {
        @SuppressWarnings("unchecked")
        @Override
        public void run() {
            while (!isTerminated.get()) {
                try {
                    ExecutorTask<Object> task = (ExecutorTask<Object>) tasksQueue.take();

                    synchronized (activeTasks) {
                        activeTasks.getAndIncrement();
                        activeTasks.notifyAll();
                    }

                    try {
                        Object result = task.get();
                        task.future().complete(result);
                    } catch (Throwable e) {
                        task.future().completeExceptionally(e);
                    } finally {
                        synchronized (activeTasks) {
                            activeTasks.getAndDecrement();
                            activeTasks.notifyAll();
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }

    private record ExecutorTask<T>(
            Supplier<T> supplier,
            CompletableFuture<T> future) implements Supplier<T> {
        @Override
            public T get() {
                return supplier.get();
            }
        }
}
