import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

public class BitmapProgram {
    private static final File OUTPUT_FILE = new File("./out/landscape.jpg");
    private static final Path OUTPUT_FILE_PATH = OUTPUT_FILE.toPath();

    public static void main(String[] args) {
        try {
            var image = ImageIO.read(Objects.requireNonNull(BitmapProgram.class.getResourceAsStream("landscape.jpg")));

            var graphics = image.createGraphics();
            graphics.setColor(Color.RED);
            graphics.setFont(new Font("JetBrains Mono", Font.PLAIN, 30));
            graphics.drawString("LOREM IPSUM DOLOR SIT AMET", 10, 10);
            graphics.setColor(Color.decode("#ffdd10"));
            graphics.fillRect(200, 250, 200, 200);
            graphics.dispose();

            OUTPUT_FILE.getParentFile().mkdirs();
            OUTPUT_FILE.createNewFile();
            var outputStream = Files.newOutputStream(OUTPUT_FILE_PATH, StandardOpenOption.WRITE);
            ImageIO.write(image, "jpg", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
