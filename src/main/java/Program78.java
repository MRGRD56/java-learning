import org.apache.commons.lang3.time.StopWatch;

public class Program78 {
    public static void main(String[] args) throws InterruptedException {
        StopWatch heapCounterStopWatch = StopWatch.createStarted();
        new HeapCounter().count();
        System.out.println("HeapCounter <-> " + heapCounterStopWatch.formatTime());

        StopWatch stackCounterStopWatch = StopWatch.createStarted();
        new StackCounter().count();
        System.out.println("StackCounter <-> " + stackCounterStopWatch.formatTime());
    }

    public static class HeapCounter implements Counter {
        private long counter = 0;

        @Override
        public void count() {
            for (long i = 0; i < 0xffffffffL; i++) {
                this.counter++;
            }
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    public static class StackCounter implements Counter {
        private long counter = 0;

        @Override
        public void count() {
            long counter = this.counter;
            for (long i = 0; i < 0xffffffffL; i++) {
                counter++;
            }
            this.counter = counter;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    interface Counter {
        void count();
        long getCounter();
    }
}
