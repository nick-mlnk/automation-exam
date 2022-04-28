package infrastructure.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;

import static infrastructure.configuration.ConfigurationManger.getPlatform;


public class MobileDriver implements WebDriverProvider {

    private static ThreadLocal<AppiumDriver> driverThreadLocal = ThreadLocal.withInitial(() -> getPlatform().getDriver());

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        return driverThreadLocal.get();
    }

    protected AppiumDriver getDriver() {
        return driverThreadLocal.get();
    }
}
