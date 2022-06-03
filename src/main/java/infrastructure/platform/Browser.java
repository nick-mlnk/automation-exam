package infrastructure.platform;

import com.codeborne.selenide.Config;
import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.WebDriver;

import java.util.Objects;


@AllArgsConstructor
@Getter
public enum Browser {

    CHROME("chrome"),
    EDGE("edge"),
    OPERA("opera"),
    FIREFOX("firefox");

    final String name;

    public static Browser from(String name) {
        if (Objects.nonNull(name)) {
            String trimmed = name.trim();
            for (Browser candidate : Browser.values()) {
                if (trimmed.equalsIgnoreCase(candidate.getName()))
                    return candidate;
            }
        }
        throw new IllegalArgumentException("\nUnsupported Browser: " + name + "\n");
    }

    public WebDriver getDriver() {
        throw new IllegalStateException("Please override this method");
    }

    public SelenideDriver getSelenideDriver() {
        Config config = new SelenideConfig()
                .browser(this.name)
                .timeout(15000);
        return new SelenideDriver(config);
    }
}
