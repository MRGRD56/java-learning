import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Program77 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            int taskNumber = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(100 * taskNumber);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Finished " + taskNumber);
                countDownLatch.countDown();
            });
        }

        executor.shutdown();

        countDownLatch.await();

        System.out.println("FINISHED!!!");
    }
}
