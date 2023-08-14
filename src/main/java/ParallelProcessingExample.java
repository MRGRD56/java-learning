import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ParallelProcessingExample {

    static class Data {
        int[] values;

        Data(int size) {
            this.values = new int[size];
            for (int i = 0; i < size; i++) {
                values[i] = i;
            }
        }

        int processChunk(int start, int end) {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += values[i];
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        final int size = 1000;
        final int numberOfThreads = 4;
        final int chunkSize = size / numberOfThreads;
        Data data = new Data(size);

        final int[] partialResults = new int[numberOfThreads];
        CyclicBarrier barrier = new CyclicBarrier(numberOfThreads, () -> {
            int total = 0;
            for (int partial : partialResults) {
                total += partial;
            }
            System.out.println("Total sum: " + total);
        });

        for (int i = 0; i < numberOfThreads; i++) {
            final int threadIndex = i;
            new Thread(() -> {
                partialResults[threadIndex] = data.processChunk(threadIndex * chunkSize, (threadIndex + 1) * chunkSize);
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}