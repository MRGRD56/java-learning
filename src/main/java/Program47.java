import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class Program47 {
    private static final String TXT_FILE = "src/main/resources/lorem-ml.txt";
    private static final Charset TXT_CHARSET = Charset.forName("GB18030");

    public static void main(String[] args) throws Exception {
        writeFile();

        try (FileInputStream fileInputStream = new FileInputStream(TXT_FILE)) {
            FileChannel fileChannel = fileInputStream.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(8 * 1024);

            while (fileChannel.read(buffer) != -1) {
                buffer.flip();

                CharBuffer decodedBuffer = TXT_CHARSET.decode(buffer);

//                System.out.println(decodedBuffer);
                while (decodedBuffer.hasRemaining()) {
                    System.out.print(decodedBuffer.get());
                }

                buffer.clear();
            }
        }
    }

    private static void writeFile() throws Exception {
        String initialText = """
                Lorem Ipsum dolor sit amet
                Лорем Имсум долор сит амет
                ロレム・イプスム・ドロール・シト・アメット
                """;

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        CharBuffer charBuffer = buffer.asCharBuffer();

        charBuffer.put(initialText);
        charBuffer.flip();

        try (FileOutputStream fileOutputStream = new FileOutputStream(TXT_FILE)) {
            FileChannel fileChannel = fileOutputStream.getChannel();

            fileChannel.write(TXT_CHARSET.encode(charBuffer));
        }
    }
}
