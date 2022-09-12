import java.io.Closeable;
import java.io.IOException;

public class Program11 {
    public static void main(String[] args) throws InterruptedException {
        label1:
        for (var i = 0; ; i++) {
            for (var j = 0; ; j++) {
                System.out.printf("i=%d j=%d\n", i, j);
                if (j == 2) {
                    break label1;
                }
            }
        }

        var hello = "A";

        var result = switch (hello) {
            case "A" -> "a";
            case "B" -> "b";
            case "C" -> "c";
            default -> null;
        };

        String result2 = null;
        switch (hello) {
            case "A":
                result2 = "a";

        }

        double bye = 23.5;

//        var byeRes = switch (bye) {
//
//        }

        if (true) {
            var obj = new AutoCloseable() {
                @Override
                public void close() {
                    System.out.println("obj closed");
                }

                @Override
                protected void finalize() throws Throwable {
                    System.out.println("obj finalized");
                    super.finalize();
                }
            };
            obj.close();
            obj = null;
        }

        System.gc();
    }
}
