package infrastructure.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CollectionUtils {

    public static <T> T getRandomFromList(Collection<T> list) {
        return list.stream()
                .findAny()
                .orElseThrow(() -> new IllegalStateException("Collection is empty."));
    }
}
