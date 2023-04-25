import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Solution {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 1111;
    private static final byte[] testPacket = {(byte) 0x01, (byte) 0x02};

    public static boolean isRegularServer() {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT)) {
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();

            outputStream.write(testPacket);
            byte readByte = (byte) inputStream.read();
            return readByte == testPacket[0];
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
