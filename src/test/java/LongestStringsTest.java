import apps.longeststringprinter.LongestStringFinder;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;

import static java.util.Arrays.asList;

public class LongestStringsTest {

    @Test
    public void testFilteringOfLongestStrings() {
        String[] arr = {"hello", "great", "min", "max", "hey", "caps"};
        List<String> longestStrings = new LongestStringFinder().find(arr);

        Assertions.assertThat(longestStrings)
                .hasSameElementsAs((asList("hello", "great")));
    }
}
