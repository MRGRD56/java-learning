import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;

public class Program44 {
    public static void main(String[] args) throws Exception {
        try (RandomAccessFile file = new RandomAccessFile("src/main/resources/image.jpeg.http", "r")) {
            long filePointer = file.getFilePointer();
            String line;
            while (!(line = file.readLine()).isEmpty()) {
                System.out.print('[');
                System.out.print(filePointer);
                System.out.print("] ");
                System.out.println(line);

                filePointer = file.getFilePointer();
            }

            byte[] imageBytes = new byte[(int) (file.length() - file.getFilePointer())];
            file.readFully(imageBytes, 0, imageBytes.length);

            Files.write(Path.of("src/main/resources/image.jpeg.http.jpeg"), imageBytes);
        }
    }
}
