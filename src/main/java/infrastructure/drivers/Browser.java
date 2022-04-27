package infrastructure.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;

public class Browser implements WebDriverProvider {

    // skip parametrization for Webdrivers, use Chrome for demo
    @Override
    public WebDriver createDriver(Capabilities desiredCapabilities) {
        ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
        HashMap<String, Object> chromePrefers = new HashMap<>();
        chromePrefers.put("profile.default_content_settings.popups", 1);
        chromePrefers.put("profile.default_content_setting_values.notifications", 1);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefers);
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disble-gpu");
        options.addArguments("--allowed-ips");

        return new ChromeDriver(options);
    }
}
