import java.util.stream.IntStream;

public class Program6 {
    public static void main(String[] args) {
        var stream = IntStream.range(0, 1);
        var list = stream.boxed().toList();
    }
}
