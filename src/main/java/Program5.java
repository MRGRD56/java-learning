public class Program5 {
    public static void main(String[] args) {
//        Object value = null;
//        var imm = (ImmutableClass) value;
//        System.out.println(imm.getA());
        var thread = runThread();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        thread.suspend();
    }

    private static Thread runThread() {
        var thread = new Thread(() -> {
            for (var i = 0; ; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("ITERATION " + i);
            }
        });

        thread.start();

        return thread;
    }
}
