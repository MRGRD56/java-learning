import org.apache.commons.lang3.RandomUtils;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Program80 {
    public static void main(String[] args) throws Exception {
        BlockingQueue<Integer> integers = new PriorityBlockingQueue<>(25, Comparator.reverseOrder());

        new Thread(() -> {
            while (true) {
                try {
                    Integer integer = integers.take();
                    System.out.println(integer);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }).start();

        for (int i = 0; i < (25 / 3); i++) {
            Thread.sleep(RandomUtils.nextInt(0, 150));
            for (int j = 0; j < 3; j++) {
                integers.offer(RandomUtils.nextInt(0, (i + j) * 25));
            }
            integers.offer(-1);
        }

        System.exit(0);
    }
}
