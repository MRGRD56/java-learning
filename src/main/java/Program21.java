import com.mrgrd56.util.fs.FileLinesIterable;

import java.nio.file.Path;
import java.util.stream.StreamSupport;

public class Program21 {
    public static void main(String[] args) {
        try (FileLinesIterable fileIterable = new FileLinesIterable(Path.of("C:\\Users\\SU\\IdeaProjects\\java-learning\\src\\main\\resources\\lines.txt"))) {
            StreamSupport.stream(fileIterable.spliterator(), false).forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
