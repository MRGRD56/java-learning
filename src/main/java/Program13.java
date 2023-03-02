import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class Program13 {
    public static void main(String[] args) {
        try (Writer writer = new OutputStreamWriter(System.out, StandardCharsets.UTF_8)) {
            writer.write("А вот так!");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
