import org.jetbrains.annotations.NotNull;

import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class Program43 {
    public static void main(String[] args) throws Exception {
        try (Socket socket = getSocket()) {
            DataInputStream responseDataInputStream = getDataInputStream(socket);

            String line;
            while (!(line = responseDataInputStream.readLine()).isEmpty()) {
                System.out.println(line);
            }

            try (OutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream("src/main/resources/image.jpeg"))) {
                responseDataInputStream.transferTo(fileOutputStream);

                fileOutputStream.flush();
            }
        }

        try (Socket socket = getSocket()) {
            DataInputStream responseDataInputStream2 = getDataInputStream(socket);
            try (OutputStream responseFileOutputStream = Files.newOutputStream(Path.of("src/main/resources/image.jpeg.http"))) {
                responseDataInputStream2.transferTo(responseFileOutputStream);
            }
        }
    }

    private static Socket getSocket() throws IOException {
        return SSLSocketFactory.getDefault().createSocket("st.mrgrd56.ru", 443);
    }

    @NotNull
    private static DataInputStream getDataInputStream(Socket socket) throws IOException {
        PrintWriter httpRequestWriter = new PrintWriter(socket.getOutputStream(), false);

        httpRequestWriter.println("GET /util/image.jpeg HTTP/1.1");
        httpRequestWriter.println("Host: st.mrgrd56.ru");
        httpRequestWriter.println("Connection: close");
        httpRequestWriter.println();

        httpRequestWriter.flush();

        InputStream responseInputStream = new BufferedInputStream(socket.getInputStream());
        DataInputStream responseDataInputStream = new DataInputStream(responseInputStream);
        return responseDataInputStream;
    }
}
