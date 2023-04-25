import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class TimestampRangeCalculator {

    public static Duration calculateTotalDuration(List<Instant[]> ranges) {
        Duration totalDuration = Duration.ZERO;
        Instant[] previousRange = null;

        for (Instant[] range : ranges) {
            Duration duration = Duration.between(range[0], range[1]);

            if (previousRange != null) {
                Instant intersectionStart = range[0].isAfter(previousRange[0]) ? range[0] : previousRange[0];
                Instant intersectionEnd = range[1].isBefore(previousRange[1]) ? range[1] : previousRange[1];
                Duration intersectionDuration = Duration.between(intersectionStart, intersectionEnd);

                if (!intersectionDuration.isNegative()) {
                    totalDuration = totalDuration.minus(intersectionDuration);
                }
            }

            totalDuration = totalDuration.plus(duration);
            previousRange = range;
        }

        return totalDuration;
    }

    public static void main(String[] args) {
        List<Instant[]> ranges1 = List.of(new Instant[][] {
                new Instant[] {Instant.parse("2023-01-01T01:00:00Z"), Instant.parse("2023-01-01T05:00:00Z")}});
        System.out.println(calculateTotalDuration(ranges1)); // 4 hours

        List<Instant[]> ranges2 = List.of(new Instant[][] {
                new Instant[] {Instant.parse("2023-01-01T01:00:00Z"), Instant.parse("2023-01-01T05:00:00Z")},
                new Instant[] {Instant.parse("2023-01-01T06:00:00Z"), Instant.parse("2023-01-01T07:00:00Z")}});
        System.out.println(calculateTotalDuration(ranges2)); // 5 hours

        List<Instant[]> ranges3 = List.of(new Instant[][] {
                new Instant[] {Instant.parse("2023-01-01T01:00:00Z"), Instant.parse("2023-01-01T05:00:00Z")},
                new Instant[] {Instant.parse("2023-01-01T04:00:00Z"), Instant.parse("2023-01-01T06:00:00Z")}});
        System.out.println(calculateTotalDuration(ranges3)); // 5 hours

        List<Instant[]> ranges4 = List.of(new Instant[][] {
                new Instant[] {Instant.parse("2023-01-01T01:00:00Z"), Instant.parse("2023-01-01T05:00:00Z")},
                new Instant[] {Instant.parse("2023-01-01T04:00:00Z"), Instant.parse("2023-01-02T06:00:00Z")},
                new Instant[] {Instant.parse("2023-01-02T04:00:00Z"), Instant.parse("2023-01-02T06:30:10Z")},
        });
        System.out.println(calculateTotalDuration(ranges4)); // 29h 30m 10s
    }
}