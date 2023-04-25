import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class HelloWorld5 {
    private static final Runtime runtime = Runtime.getRuntime();

    public static String greet() throws Exception {
        Process shell = runtime.exec("/bin/bash");

        try {
            InputStream shellInput = shell.getInputStream();
            OutputStream shellOutput = shell.getOutputStream();

            shellInput.skipNBytes(shellInput.available());

            Scanner scanner = new Scanner(shellInput);

            shellOutput.write("echo 'hello world!'\n".getBytes(StandardCharsets.UTF_8));
            shellOutput.flush();

            return scanner.nextLine();
        } finally {
            shell.destroy();
        }
    }

    private interface Shell {

    }

    private static class BashShell implements Shell {

    }
}