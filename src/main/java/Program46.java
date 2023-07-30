import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;

public class Program46 {
    private static final String MULTIPART_BOUNDARY = "------------------------d74496d66958873e";
    private static final String MULTIPART_BOUNDARY_EDGE = "--";
    private static final String MULTIPART_BOUNDARY_SEPARATOR = MULTIPART_BOUNDARY_EDGE + MULTIPART_BOUNDARY;
    private static final String MULTIPART_BOUNDARY_SEPARATOR_END = MULTIPART_BOUNDARY_SEPARATOR + MULTIPART_BOUNDARY_EDGE;

    public static void main(String[] args) throws Exception {
        try (Socket socket = SSLSocketFactory.getDefault().createSocket("api.mrgrd56.ru", 443)) {
            PrintStream requestStream = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));

            ByteArrayOutputStream preRequestBodyOutputStream = new ByteArrayOutputStream();
            PrintStream preRequestBodyPrintStream = new PrintStream(preRequestBodyOutputStream);

            preRequestBodyPrintStream.println(MULTIPART_BOUNDARY_SEPARATOR);
            preRequestBodyPrintStream.println("Content-Disposition: form-data; name=\"image\"; filename=\"404.jpeg\"");
            preRequestBodyPrintStream.println("Content-Type: image/jpeg");
            preRequestBodyPrintStream.println();
            try (InputStream imageInputStream = Program46.class.getResourceAsStream("/404.jpeg")) {
                assert imageInputStream != null;
                imageInputStream.transferTo(preRequestBodyPrintStream);
                preRequestBodyPrintStream.println();
            }

            preRequestBodyPrintStream.println(MULTIPART_BOUNDARY_SEPARATOR);
            preRequestBodyPrintStream.println("Content-Disposition: form-data; name=\"recognitionLanguage\"");
            preRequestBodyPrintStream.println();
            preRequestBodyPrintStream.println("rus");

            preRequestBodyPrintStream.println(MULTIPART_BOUNDARY_SEPARATOR);
            preRequestBodyPrintStream.println("Content-Disposition: form-data; name=\"thresholdValue\"");
            preRequestBodyPrintStream.println();
            preRequestBodyPrintStream.println("170");

            preRequestBodyPrintStream.println(MULTIPART_BOUNDARY_SEPARATOR);
            preRequestBodyPrintStream.println("Content-Disposition: form-data; name=\"thresholdMaxval\"");
            preRequestBodyPrintStream.println();
            preRequestBodyPrintStream.println("255");

            preRequestBodyPrintStream.println(MULTIPART_BOUNDARY_SEPARATOR);
            preRequestBodyPrintStream.println("Content-Disposition: form-data; name=\"thresholdType\"");
            preRequestBodyPrintStream.println();
            preRequestBodyPrintStream.println("BINARY");

            preRequestBodyPrintStream.println(MULTIPART_BOUNDARY_SEPARATOR);
            preRequestBodyPrintStream.println("Content-Disposition: form-data; name=\"cropMain\"");
            preRequestBodyPrintStream.println();
            preRequestBodyPrintStream.println("true");

            preRequestBodyPrintStream.println(MULTIPART_BOUNDARY_SEPARATOR_END);

            ByteArrayOutputStream preRequestOutputStream = new ByteArrayOutputStream();
            PrintStream preRequestPrintStream = new PrintStream(preRequestOutputStream);

            preRequestPrintStream.println("POST /ocr/recognize/prepared HTTP/1.1");
            preRequestPrintStream.println("Host: api.mrgrd56.ru");
            preRequestPrintStream.println("Connection: close");
            preRequestPrintStream.println("Content-Type: multipart/form-data; boundary=" + MULTIPART_BOUNDARY);
            preRequestPrintStream.println("Content-Length: " + preRequestBodyOutputStream.size());
            preRequestPrintStream.println();
            preRequestBodyOutputStream.writeTo(preRequestPrintStream);

            System.out.writeBytes(preRequestOutputStream.toByteArray());
            System.out.println("\n");

            preRequestOutputStream.writeTo(requestStream);
            requestStream.flush();

            socket.getInputStream().transferTo(System.out);
        }
    }
}
