import org.apache.commons.lang3.time.StopWatch;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Program42 {
    public static void main(String[] args) throws Exception {
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream("src/main/resources/file.bin"))) {
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            for (int i = 0; i < 1_000_000; i++) {
                dataOutputStream.writeInt(i);
            }

            outputStream.flush();
        }

        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream("src/main/resources/file2.txt"))) {
            Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);

            for (int i = 0; i < 1_000_000; i++) {
                writer.write(Integer.toString(i));
            }

            outputStream.flush();
        }

        try (InputStream inputStream = new FileInputStream("src/main/java/Program42.java")) {
            inputStream.transferTo(System.out);
//            byte[] buffer = new byte[2048];
//            int bytesRead;
//
//            while ((bytesRead = inputStream.read(buffer)) != -1) {
//                System.out.write(buffer, 0, bytesRead);
//            }
        }

        StopWatch stopWatch = StopWatch.createStarted();

        PrintStream out = new PrintStream(new BufferedOutputStream(System.out), false, StandardCharsets.UTF_8);
//        PrintStream out = System.out;

        try (InputStream inputStream = new FileInputStream("src/main/resources/file.bin")) {
            DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(inputStream));

            out.print("[");

            try {
                out.print(dataInputStream.readInt());

                while (true) {
                    int number = dataInputStream.readInt();

                    out.print(", ");
                    out.print(number);
                }
            } catch (EOFException e) {
                out.println("]");
                out.flush();
            }
        }

        System.out.println("\nCompleted in " + stopWatch.formatTime());
    }
}
