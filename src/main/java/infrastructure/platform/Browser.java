package infrastructure.platform;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.util.Objects;

@AllArgsConstructor
@Getter
public enum Browser {

    CHROME("chrome") {
        @Override
        public WebDriver getDriver() {
            WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
            return new ChromeDriver();
        }
    },
    EDGE("edge") {
        @Override
        public WebDriver getDriver() {
            WebDriverManager.getInstance(DriverManagerType.EDGE).setup();
            return new EdgeDriver();
        }
    },
    OPERA("opera") {
        @Override
        public WebDriver getDriver() {
            WebDriverManager.getInstance(DriverManagerType.OPERA).setup();
            return new OperaDriver();
        }
    },
    FIREFOX("firefox") {
        @Override
        public WebDriver getDriver() {
            WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
            return new FirefoxDriver();
        }
    };

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
}
