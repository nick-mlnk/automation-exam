package apps.longeststringprinter;

import lombok.SneakyThrows;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LongestStringFinder implements IStringFinder {

    @SneakyThrows
    @Override
    public List<String> find(String[] array) {
        if (array.length == 0) {
            throw new IllegalAccessException("Array should be not empty.");
        }
        int strLength = Stream.of(array)
                .max(Comparator.comparingInt(String::length))
                .get()
                .length();
        return Stream.of(array)
                .filter(str -> str.length() == strLength)
                .collect(Collectors.toList());
    }
}
