import java.io.IOException;

public class Program2 {
//    private static int getSum(int number1, int... number2, Function<> function) {
//        var clazz = number2.getClass();
//    }

    public static void main(String[] args) throws IOException {
        var inputStream = Program2.class.getClassLoader().getResourceAsStream("text.txt");

        inputStream.mark(0);

        var bytes = new byte[inputStream.available()];
        while (inputStream.read(bytes) != -1) {}

        inputStream.reset();

        var bytes2 = inputStream.readAllBytes();

        System.out.writeBytes(bytes2);
    }
}
