public class StackOverflowWithoutRecursion {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10); // to simulate some work
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        StackTraceElement[] stackTrace;
        while (true) {
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stackTrace = thread.getStackTrace();
            if (stackTrace.length > 1000) { // arbitrary large number
                throw new StackOverflowError();
            }
        }
    }
}
