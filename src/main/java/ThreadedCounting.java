import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadedCounting {

    private static final ExecutorService executor1 = Executors.newSingleThreadExecutor();
    private static final ExecutorService executor2 = Executors.newSingleThreadExecutor();

    public static void countInThreads(Counter counter) {
        List<Callable<Void>> tasks = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            int number = i;

            if (number % 3 == 0) {
                counter.count(number);
            } else if (number % 3 == 1) {
                executor1.submit(() -> {
                    counter.count(number);
                    return null;
                });
            } else {
                executor2.submit(() -> {
                    counter.count(number);
                    return null;
                });
            }
        }
    }
}

class Counter {
    void count(int number) { }

    public static void main(String[] args) {
        ThreadedCounting.countInThreads(new Counter());
        Object n = null;
    }
}