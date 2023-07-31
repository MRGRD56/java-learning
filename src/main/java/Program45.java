import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Program45 {
    public static void main(String[] args) throws Exception {
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/image.jpeg.http")) {
            FileChannel channel = fileInputStream.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(105);
            channel.read(buffer);
            buffer.flip();

            while (buffer.hasRemaining()) {
                System.out.write(Byte.toUnsignedInt(buffer.get()));
            }

            System.out.flush();
        }

        System.out.print("\n\n------\n\n");

        try (FileOutputStream headersFileOutputStream = new FileOutputStream("src/main/resources/image-ch.http");
             FileChannel headersFileChannel = headersFileOutputStream.getChannel()) {
            try (SocketChannel socketChannel = SocketChannel.open()) {
                socketChannel.connect(new InetSocketAddress("st.mrgrd56.ru", 80));

                StringBuilder requestBuilder = new StringBuilder();

                requestBuilder.append("GET /util/image.jpeg HTTP/1.1\r\n");
                requestBuilder.append("Host: st.mrgrd56.ru\r\n");
                requestBuilder.append("Connection: close\r\n");
                requestBuilder.append("X-Enforce-Protocol: HTTP\r\n");
                requestBuilder.append("\r\n");

                socketChannel.write(ByteBuffer.wrap(requestBuilder.toString().getBytes(StandardCharsets.UTF_8)));

                ByteBuffer buffer = ByteBuffer.allocate(8 * 1024);

                responseLoop:
                while (socketChannel.read(buffer) != -1) {
                    buffer.flip();

                    while (buffer.hasRemaining()) {
                        int byteRead = buffer.get();
                        System.out.write(byteRead);

                        if (byteRead == '\r') {
                            if (buffer.get() == '\n') {
                                if (buffer.get() == '\r') {
                                    if (buffer.get() == '\n') {
                                        System.out.println("\r\n\r\n<contents>");
                                        System.out.flush();

                                        int entireLimit = buffer.limit();
                                        int headersEndPosition = buffer.position();

                                        buffer.limit(buffer.position());
                                        buffer.position(0);
                                        headersFileChannel.write(buffer);

                                        buffer.limit(entireLimit);
                                        buffer.position(headersEndPosition);

                                        break responseLoop;
                                    } else {
                                        buffer.position(buffer.position() - 3);
                                    }
                                } else {
                                    buffer.position(buffer.position() - 2);
                                }
                            } else {
                                buffer.position(buffer.position() - 1);
                            }
                        }
                    }

                    buffer.limit(buffer.position());
                    buffer.position(0);
                    headersFileChannel.write(buffer);

                    buffer.clear();
                }

                try (FileOutputStream bodyFileOutputStream = new FileOutputStream("src/main/resources/image-ch.jpeg");
                     FileChannel bodyFileChannel = bodyFileOutputStream.getChannel()) {
                    bodyFileChannel.write(buffer);
                    buffer.clear();

                    while (socketChannel.read(buffer) != -1) {
                        buffer.flip();
                        bodyFileChannel.write(buffer);
                        buffer.clear();
                    }
                }
            }
        }
    }
}
