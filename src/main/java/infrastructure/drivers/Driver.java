package infrastructure.drivers;

import com.codeborne.selenide.WebDriverProvider;
import infrastructure.configuration.ConfigurationManger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;


public class Driver implements WebDriverProvider {

    @Override
    public WebDriver createDriver(Capabilities desiredCapabilities) {
        return ConfigurationManger.getBrowser().getDriver();
    }
}
