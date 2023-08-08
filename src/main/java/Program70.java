import dev.b37.mgutils.concurrent.TaskInvoker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Program70 {
    private static final List<Integer> integers = new ArrayList<>(100_000);

    private static final ExecutorService executor = Executors.newFixedThreadPool(100);

    public static void main(String[] args) {
        TaskInvoker<Void> taskInvoker = new TaskInvoker<>(executor);

        for (int i = 0; i < 110_000; i++) {
            int number = i;
            taskInvoker.submit(() -> {
                if (integers.size() < 100_000) {
                    synchronized (integers) {
                        if (integers.size() < 100_000) {
                            integers.add(number);
                        }
                    }
                } else {
                    System.out.println("Did not add " + number);
                }
            });
        }

        taskInvoker.completeAllVoid();
        executor.shutdown();

        System.out.println("List size: " + integers.size());
    }
}
