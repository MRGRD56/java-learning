import org.apache.commons.lang3.RandomUtils;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Program80_1 {
    public static void main(String[] args) throws Exception {
        BlockingQueue<Integer> integers = new PriorityBlockingQueue<>(25, Comparator.reverseOrder());

        new Thread(() -> {
            while (true) {
                Integer integer;
                while ((integer = integers.poll()) != null) {
                    System.out.println(integer);
                }

                System.out.println("No elements yet");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
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
