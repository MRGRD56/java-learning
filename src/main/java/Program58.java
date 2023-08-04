import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.*;

public class Program58 {
    public static void main(String[] args) throws Exception {
        List<Checksum> outputChecksums = Arrays.asList(new CRC32(), new CRC32(), new CRC32());

        try (FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/p58.zip");
             ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream, StandardCharsets.UTF_8)) {
            zipOutputStream.setLevel(Deflater.BEST_COMPRESSION);

            for (int i = 0; i < 3; i++) {
                CheckedOutputStream checkedOutputStream = new CheckedOutputStream(zipOutputStream, outputChecksums.get(i));
                Writer writer = new BufferedWriter(new OutputStreamWriter(checkedOutputStream, StandardCharsets.UTF_8));

                zipOutputStream.putNextEntry(new ZipEntry(i + ".txt"));

                for (int j = 0; j < 100; j++) {
                    writer.write(Integer.toString(i));
                }

                writer.write("\n\n");

                for (int j = 0; j < 5; j++) {
                    writer.write(Math.random() + "\n");
                }

                writer.write("\nEOF\n");

                writer.flush();
            }
        }

        List<Checksum> inputChecksums = new ArrayList<>(3);

        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/p58.zip");
             ZipInputStream zipInputStream = new ZipInputStream(fileInputStream, StandardCharsets.UTF_8)) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                Checksum checksum = new CRC32();
                inputChecksums.add(checksum);
                CheckedInputStream checkedInputStream = new CheckedInputStream(zipInputStream, checksum);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(checkedInputStream));

                System.out.printf("\n=====[%s]=====\n", zipEntry.getName());
                int charRead;
                while ((charRead = bufferedReader.read()) != -1) {
                    System.out.print((char) charRead);
                }
            }
        }

        System.out.println("\n");

        for (int i = 0; i < inputChecksums.size(); i++) {
            Checksum outputChecksum = outputChecksums.get(i);
            Checksum inputChecksum = inputChecksums.get(i);

            long outputChecksumValue = outputChecksum.getValue();
            long inputChecksumValue = inputChecksum.getValue();

            System.out.printf("[Checksum-%d] %s -> %s = %s\n",
                    i,
                    Long.toString(outputChecksumValue, 16),
                    Long.toString(inputChecksumValue, 16),
                    outputChecksumValue == inputChecksumValue ? "VALID" : "INVALID");
        }

        try (ZipFile zipFile = new ZipFile("src/main/resources/p58.zip")) {
            zipFile.stream().forEachOrdered((zipEntry) -> {
                Checksum checksum = new CRC32();

                try (InputStream zipEntryInputStream = zipFile.getInputStream(zipEntry);
                     CheckedInputStream checkedInputStream = new CheckedInputStream(zipEntryInputStream, checksum)) {
                    System.out.println(zipEntry.getName() + "\n");
                    checkedInputStream.transferTo(System.out);
                    System.out.println("\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Checksum = " + Long.toString(checksum.getValue(), 16));
            });
        }
    }
}
