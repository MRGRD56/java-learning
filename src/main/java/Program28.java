public class Program28 {
    public static void main(String[] args) {
        new Program28().foo();
    }

    public void foo() {
        class MyRunnable implements Runnable {
            @Override
            public void run() {
                System.out.println("Hello World!");
            }
        }

        Runnable[] runnables = new Runnable[2];

        for (int i = 0; i < runnables.length; i++) {
            runnables[i] = this::foo;
        }

        var obj = new Object() {
            void bar() {
                System.out.println("BAR!");
            }
        };

        obj.bar();

        System.out.printf("Equals: %s\n", runnables[0] == runnables[1]);
    }
}