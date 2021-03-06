package Part1.BaseClasses;

import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Albert Cañellas and Laura Romero.
 * CustomCollector class
 */
public class CustomCollector {

    public static <T> Collector<T, ?, T> toSingleton() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() != 1) {
                        throw new IllegalStateException();
                    }
                    return list.get(0);
                }
        );
    }

}
