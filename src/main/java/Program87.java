import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.StopWatch;

import java.util.*;

public class Program87 {
    public static void main(String[] args) {
        int[] numbers = new int[5_000_000];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = RandomUtils.nextInt();
        }

        System.out.println("Starting");

        System.out.println("ArraySort took " + testArraySort(numbers).formatTime());
        System.out.println("PriorityQueue took " + testPriorityQueue(numbers).formatTime());
    }

    private static StopWatch testArraySort(int[] numbers) {
        List<Integer> sorted = new ArrayList<>(numbers.length);

        StopWatch stopWatch = StopWatch.createStarted();

        for (int number : numbers) {
            sorted.add(number);
        }

        sorted.sort(Integer::compareTo);

        for (Integer number : sorted) {
            // do nothing
        }

        stopWatch.stop();
        return stopWatch;
    }

    private static StopWatch testPriorityQueue(int[] numbers) {
        Queue<Integer> sorted = new PriorityQueue<>(numbers.length);

        StopWatch stopWatch = StopWatch.createStarted();

        for (int number : numbers) {
            sorted.add(number);
        }

        Integer integer;
        while ((integer = sorted.poll()) != null) {
            // do nothing
        }

        stopWatch.stop();
        return stopWatch;
    }
}
