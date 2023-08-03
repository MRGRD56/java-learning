import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.*;

public class Program57 {
    private static final Path filePath = Path.of("src/main/resources/p57.txt.gz");

    public static void main(String[] args) throws Exception {
        Checksum writeChecksum;

        try (OutputStream outputStream = Files.newOutputStream(filePath);
             CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new CRC32());
             CustomGZIPOutputStream gzipOutputStream = new CustomGZIPOutputStream(checkedOutputStream, true);
             Writer writer = new BufferedWriter(new OutputStreamWriter(gzipOutputStream, StandardCharsets.UTF_8))) {
            gzipOutputStream.setLevel(Deflater.BEST_COMPRESSION);
            writer.write(TEXT);

            writeChecksum = checkedOutputStream.getChecksum();
        }

        System.out.println("WRITE-CHECKSUM " + writeChecksum.getValue());

        Checksum readChecksum;

        try (InputStream inputStream = Files.newInputStream(filePath);
             CheckedInputStream checkedInputStream = new CheckedInputStream(inputStream, new CRC32());
             GZIPInputStream gzipInputStream = new GZIPInputStream(checkedInputStream);
             BufferedReader reader = new BufferedReader(new InputStreamReader(gzipInputStream))) {
            String lineRead;
            while ((lineRead = reader.readLine()) != null) {
                System.out.println(lineRead);
            }

            readChecksum = checkedInputStream.getChecksum();
        }

        System.out.println("READ-CHECKSUM " + readChecksum.getValue());
    }

    private static class CustomGZIPOutputStream extends GZIPOutputStream {

        public CustomGZIPOutputStream(OutputStream out, int size) throws IOException {
            super(out, size);
        }

        public CustomGZIPOutputStream(OutputStream out, int size, boolean syncFlush) throws IOException {
            super(out, size, syncFlush);
        }

        public CustomGZIPOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        public CustomGZIPOutputStream(OutputStream out, boolean syncFlush) throws IOException {
            super(out, syncFlush);
        }

        public void setLevel(int level) {
            def.setLevel(level);
        }
    }

    private static final String TEXT = """
            [Verse 1]
            Time will come back, turning upside down
            You live in the maze of all your sadness and pain
            If you want more games, look beyond the gate
            I believe there is an unlimited beautiful world out there
            Come what may, I'll reach there
            (Although I know it is not easy)
            An experiment was a failure
            The artificer of the universe
            (Everything is in the hand of God)
            Who wrote this story? I gotta play
                        
            [Pre-Chorus]
            We share the same fate
            Do you believe in this fate?
            There is nobody else like you
            Who takes my heart away
                        
            [Chorus]
            The place where one road forks endlessly
            From the past to the future, an infinite universe
            I'll wipe my tears to smile once again
            I recognize your face, your voice
            This story has just begun
            The endless loop world to other side
            You might also like
            
            [Verse 2]
            Falsifying records, searching for proofs
            Feeling a strange sensation underneath my skin
            Far from reality, a mysterious thing
            Gravity acts on everything in the cosmos
            Not a dream, yet a reality
            (To prepare one for one's cause)
            Is all coincidence, a trick of God?
            I will face it with defiance
            (Everything is in the black hole)
            There's a reason that I never lose
                        
            [Pre-Chorus]
            We share the same fate
            Do you believe in this fate?
            There is nobody else like you
            Who takes my heart away
                        
            [Chorus]
            The place where a lot of feelings intersect
            From the past to the future, an infinite universe
            I have the ability to change destiny
            I recognize your face, your voice
            This story has just begun
            The endless loop world to other side
            [Instrumental Bridge]
                        
            [Chorus]
            The place where one road forks endlessly
            From the past to the future, an infinite universe
            I'll wipe my tears to smile once again
            I recognize your face, your voice
            This story has just begun
            The endless loop world to other side
            """;
}
