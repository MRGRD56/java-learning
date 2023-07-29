import org.apache.commons.lang3.StringUtils;
import org.sparkproject.guava.io.PatternFilenameFilter;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Program41 {
    public static void main(String[] args) {
        File directory = new File("src/main/java");
        System.out.println(directory.getAbsolutePath());

        Pattern pattern = Pattern.compile("^Program(\\d*)\\.java$");

//        File[] files = directory.listFiles((dir, name) -> pattern.matcher(name).matches());
//        File[] files = directory.listFiles(new PatternFilenameFilter(pattern));

        FilenameFilter filenameFilter = null;

        for (int i = 0; i < 5; i++) {
            filenameFilter = new FilenameFilter() {
                private static final Pattern pattern;

                static {
                    System.out.println("FilenameFilter static");
                    pattern = Pattern.compile("^Program(\\d*)\\.java$");
                }

                @Override
                public boolean accept(File dir, String name) {
                    return pattern.matcher(name).matches();
                }
            };
        }

        File[] files = directory.listFiles(filenameFilter);

        assert files != null;
        Arrays.sort(files, Comparator.comparing(file -> {
            Matcher matcher = pattern.matcher(file.getName());
            if (!matcher.matches()) {
                return 0;
            }

            String number = matcher.group(1);

            if (StringUtils.isBlank(number)) {
                return 0;
            }

            return Integer.parseInt(number);
        }));

        for (File file : files) {
            System.out.println(file.getName());
        }
    }
}
