import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Program6 {
    public static void main(String[] args) {
//        var stream = IntStream.range(0, 1);
//        var list = stream.boxed().toList();
        var map1to2 = Map.<Long, List<Long>>ofEntries(
                Map.entry(1L, List.of(12L, 13L, 14L)),
                Map.entry(2L, List.of(12L, 13L, 15L, 16L)),
                Map.entry(3L, List.of(10L, 11L, 12L))
        );

        System.out.println();

        var map2to1 = new LinkedHashMap<Long, List<Long>>();

        map1to2.forEach((level1, level1Children) -> {
            level1Children.forEach(level2 -> {
                map2to1.compute(level2, (k, v) -> {
                    if (v == null) {
                        return new ArrayList<>(List.of(level1));
                    }

                    v.add(level1);
                    return v;
                });
            });
        });

        var item1level = List.of(12L, 24L);
        var res = (CollectionUtils.isNotEmpty(item1level) ? String.format(" AND r.items && '{%s}' ",
                item1level.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(","))) : " ");
    }
}
