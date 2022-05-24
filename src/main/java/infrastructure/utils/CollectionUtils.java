package infrastructure.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CollectionUtils {

    public static <T> T getRandomFromList(List<T> list) {
        Collections.shuffle(list);
        return list.stream().findAny().orElseThrow(() -> new IllegalStateException("List is empty."));
    }

    public static void verifyCollectionIsNotEmpty(Collection collection) {
        if (collection.isEmpty()) {
            throw new IllegalStateException("Collection is empty");
        }
    }
}
