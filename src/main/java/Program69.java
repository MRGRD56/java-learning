public class Program69 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.err.println("Caught InterruptedException");
                    Thread.currentThread().interrupt();
                    return;
                }

                System.out.println("Thread progress " + i);

                if (i == 3) {
                    Thread.currentThread().interrupt();
                    System.out.println("Interrupted on " + i);
                }
            }
        });

        thread.start();

        try {
            thread.join();
            System.out.println("JOIN SUCCESS");
        } catch (InterruptedException e) {
            System.out.println("JOIN INTERRUPT");
        }

        System.out.println("Finished");
    }
}
