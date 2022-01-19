import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Program {
    public static void main(String[] args) throws IOException {
        var text = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
        final var fileName = "./hello.txt";
        final var filePath = Path.of(fileName);

        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }

        try (var stream = new FileOutputStream(fileName)) {
            var buffer = text.getBytes(StandardCharsets.UTF_8);
            stream.write(buffer);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        //Or just:
        // Files.writeString(filePath, text);

        try (var stream = new FileInputStream(fileName)) {
            var bytes = stream.readAllBytes();
            var string = new String(bytes, StandardCharsets.UTF_8);
            System.out.println(string);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        //Or just:
        // var string = Files.readString(filePath);
        // System.out.println(string);
    }
}
