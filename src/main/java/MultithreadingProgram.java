import com.mrgrd56.common.ObservableHashSet;

import javax.management.monitor.Monitor;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class MultithreadingProgram {
    private static void simulateWork(String label) {
        System.out.println(label + " Simulating work start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(label + " Simulating work end");
    }

    private static List<Double> getBigData() {
        return Stream.generate(Math::random).limit(1_000_000).sorted(Double::compareTo).toList();
    }

    private static void doHardWork() {
//        var numbers = getBigData();
//        numbers.parallelStream().reduce(0D, Double::sum).
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
//        var numbers = getBigData();
//        var stopwatch = Stopwatch.startNew();
//        var sum = numbers.parallelStream().reduce(0D, Double::sum);
//        stopwatch.stopAndPrintElapsed("parallel stream");
//        stopwatch.restart();
//        var sum2 = numbers.stream().mapToDouble(value -> value).average();
//        stopwatch.stopAndPrintElapsed("usual stream");
//        System.out.println(sum2.getAsDouble());
//        for (var i = 0; i < 15; i++) {
//            var stopwatch = Stopwatch.startNew();
//            var count = (long) Math.pow(10, i);
//            var avg = DoubleStream.generate(() -> Math.random() * 2).limit(count).average().getAsDouble();
//            stopwatch.stop();
//            var elapsedSeconds = stopwatch.elapsedSeconds();
//            System.out.printf("%d gives %f takes %f\n", count, avg, elapsedSeconds);
//        }
        var workingThreads = new ObservableHashSet<Integer>((set) -> {
//            System.out.println(String.join(" ", set.stream().map(Object::toString).toList()));
        });

//        Supplier<ObservableHashSet<Integer>> getWorkingThreads = () -> workingThreads;

        var infoThread = new Thread(() -> {
            while (true) {
                synchronized (workingThreads) {
                    System.out.println(String.join(" ", workingThreads.stream().map(Object::toString).toList()));
                }
                sleep(50);
            }
        });
        infoThread.start();

        var semaphore = new Semaphore(2, true);

        for (var i = 0; i < 5; i ++) {
            final var iCopy = i;
            new Thread(() -> {
                semaphore.acquireUninterruptibly();
                workingThreads.add(iCopy);
                sleep(1000);
                workingThreads.remove(iCopy);
                semaphore.release();

                if (iCopy >= 4) {
                    infoThread.interrupt();
                }
            }).start();
        }
    }
}
