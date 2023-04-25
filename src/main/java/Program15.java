import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Program15 {
    public static void main(String[] args) {
        Writer writer = new Writer();

        Writeable proxy = (Writeable) Proxy.newProxyInstance(
                writer.getClass().getClassLoader(),
                writer.getClass().getInterfaces(),
                (proxy1, method, args1) -> {
                    if (method.getName().equals("write")) {
                        System.out.println("Modified write: " + args1[0].toString().toUpperCase());
                        return null;
                    }
                    return method.invoke(writer, args1);
                });

        ((Writeable) proxy).write("Hello, world!"); // Calls the modified write method

        System.out.println("Original class: " + proxy.getClass()); // Outputs "Original class: class ...$Proxy0"
        System.out.println("Original class: " + Proxy.getInvocationHandler(proxy).getClass()); // Outputs "Original class: class ...$1"

        int[] array = new int[10];
        array.getClass();

    }

    private interface Writeable {
        void write(String text);
    }

    private static class Writer implements Writeable {
        public void write(String text) {
            System.out.println("Writing as Writeable: " + text);
        }
    }
}
