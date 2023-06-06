import lombok.SneakyThrows;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Random;

public class Program30 {
    private static final Random random = new Random();

    public static void main(String[] args) throws Exception {
        List<Program30> programs = List.of(
            new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {},new Program30() {}
        );

//        try {
//            throwEx(100);
//        } catch (Exception e) {
//            throw e;
//        }

        try {
            Program30 p30 = null;
            p30.getClass();
        } catch (NullPointerException e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            System.err.println(sw.toString());
            e.fillInStackTrace();
            throw e;
        }

        class HWHW {
            @SneakyThrows
            public void doStuff() {
                throw new IOException("IO Exception!!!");
            }
        }

        HWHW hwhw = new HWHW();

        try {
            hwhw.doStuff();
        } catch (Exception e) {
            if (e instanceof IOException ioe) {
                ioe.printStackTrace();
                return;
            }

            throw e;
        }

//        programs.forEach(program -> {
//            System.out.println(program.getClass());
//        });
    }

    private static void throwEx(int steps) throws Exception {
        if (steps <= 0) {
            throw new Exception() {
                @Override
                public synchronized Throwable fillInStackTrace() {
                    if (getStackTrace().length > 0) {
                        return this;
                    }

                    return super.fillInStackTrace();
                }
            };
        }

        throwEx(steps - 1);
    }

    public static class Hello {

    }
}

//class Program30$Hello {
//
//}