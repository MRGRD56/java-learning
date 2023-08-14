import dev.b37.mgutils.concurrent.TaskInvoker;

import java.util.concurrent.*;

public class Program64 {
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    private static final SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();
    private static final ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(1);
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
    private static final CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        TaskInvoker<Void> taskInvoker = new TaskInvoker<>(executor);

        taskInvoker.submit(() -> {
            sleep(200);
            System.out.println("SynchronousQueue Thread-1 taking");
            try {
                synchronousQueue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sleep(200);
            System.out.println("SynchronousQueue Thread-1 has taken");
        });

        taskInvoker.submit(() -> {
            sleep(1000);
            System.out.println("SynchronousQueue Thread-2 putting");
            try {
                synchronousQueue.put(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sleep(200);
            System.out.println("SynchronousQueue Thread-2 has put");
        });

        taskInvoker.completeAllVoid();

        taskInvoker.submit(() -> {
            sleep(200);
            System.out.println("ArrayBlockingQueue Thread-1 taking");
            try {
                arrayBlockingQueue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sleep(200);
            System.out.println("ArrayBlockingQueue Thread-1 has taken");
        });

        taskInvoker.submit(() -> {
            sleep(1000);
            System.out.println("ArrayBlockingQueue Thread-2 putting");
            try {
                arrayBlockingQueue.put(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sleep(200);
            System.out.println("ArrayBlockingQueue Thread-2 has put");
        });

        taskInvoker.completeAllVoid();

        taskInvoker.submit(() -> {
            sleep(200);
            System.out.println("CyclicBarrier Thread-1 taking");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sleep(200);
            System.out.println("CyclicBarrier Thread-1 has taken");
        });

        taskInvoker.submit(() -> {
            sleep(1000);
            System.out.println("CyclicBarrier Thread-2 putting");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sleep(200);
            System.out.println("CyclicBarrier Thread-2 has put");
        });

        taskInvoker.completeAllVoid();

        taskInvoker.submit(() -> {
            sleep(200);
            System.out.println("CountDownLatch Thread-1 taking");
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sleep(200);
            System.out.println("CountDownLatch Thread-1 has taken");
        });

        taskInvoker.submit(() -> {
            sleep(1000);
            System.out.println("CountDownLatch Thread-2 putting");
            countDownLatch.countDown();
            sleep(200);
            System.out.println("CountDownLatch Thread-2 has put");
        });

        taskInvoker.completeAllVoid();

        executor.shutdown();
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
