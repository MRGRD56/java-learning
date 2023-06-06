import com.fazecast.jSerialComm.SerialPort;

import java.util.Arrays;

public class Program27 {
    public static void main(String[] args) {
        SerialPort[] ports = SerialPort.getCommPorts();
        SerialPort port = Arrays.stream(ports)
                .filter(p -> {
                    return !"COM3".equals(p.getSystemPortName()) && !"COM4".equals(p.getSystemPortName());
                })
                .findFirst()
                .orElseThrow();

        if (!port.openPort()) {
            throw new RuntimeException("Unable to open the serial port");
        }

        port.setComPortParameters(9600, 8, 1, 0);
        port.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

        byte[] buffer = new byte[64];
        int bytesRead;
        while ((bytesRead = port.readBytes(buffer, buffer.length)) != -1) {
            System.out.write(buffer, 0, bytesRead);
        }

        Object __null = null;
    }
}