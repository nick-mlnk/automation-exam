package web;

import infrastructure.utils.CollectionUtils;
import org.testng.annotations.Test;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UpdatedJavaTest {

    Set<String> set = Set.of("chrome", "firefox", "opera"); // the same applicable for list/map

    @Test
    public void testOfFunctionality() {
        String browser = CollectionUtils.getRandomFromList(set);
        assertThat(browser)
                .as("Browser from Set")
                .isIn(set);
    }

    @Test
    public void testOptional() {
        Optional<String> browser3 = Optional.ofNullable(null);
        Optional<String> browser1 = Optional.ofNullable("chrome");
        Optional<String> browser2 = Optional.ofNullable("safari");

        List<Optional<String>> browsers = List.of(browser1, browser2, browser3);

        List<String> customerBrowsers = browsers.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());

        assertThat(customerBrowsers)
                .as("Customer supported Browsers")
                .containsOnly("safari", "chrome");
    }

    @Test
    public void testLocalVariableType() {
        var str = "JAVA";
        assertThat(str).isExactlyInstanceOf(String.class);
    }

    @Test
    public void testModifyCopyOfSetThrowsException() {
        assertThatThrownBy(() -> {
            Set<String> browsers = Set.copyOf(set); // ImmutableCollection
            browsers.add("edge");
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void testModifyToUnmodifiableSetThrowsException() {
        assertThatThrownBy(() -> {
            Set<String> browsers = set.stream()
                    .collect(Collectors.toUnmodifiableSet()); // ImmutableCollection
            browsers.add("edge");
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void testFilesReadWrite() throws IOException {
        Path tempFilePath = Files.createTempFile(Path.of("src/test/resources"), "demo", ".txt");
        Path filePath = Files.writeString(tempFilePath, "Sample text");
        String fileContent = Files.readString(filePath);
        assertThat(fileContent).isEqualTo("Sample text");
    }

    @Test
    public void testNotPredicate() {
        var browsers = set.stream()
                .filter(Predicate.not(String::isEmpty))
                .collect(Collectors.toList());
        assertThat(browsers).hasSize(set.size());
    }

    @Test
    public void testLocalVarForLambda() {
        var browsersListAsString = List.of("chrome", "firefox", "opera")
                .stream()
                .map((@Nonnull var browser) -> browser.toUpperCase())
                .collect(Collectors.joining(", "));
        assertThat(browsersListAsString).isEqualTo("CHROME, FIREFOX, OPERA");
    }

}
