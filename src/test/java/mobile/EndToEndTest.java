package mobile;

import apps.mobile.ui.LoginScreen;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import infrastructure.drivers.MobileDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static infrastructure.configuration.ConfigurationManger.getEnv;
import static infrastructure.configuration.ConfigurationManger.getPlatform;
import static org.testng.internal.Utils.log;

public class EndToEndTest extends MobileDriver {

    @BeforeClass
    public void beforeE2ESetup() {
        Configuration.browser = MobileDriver.class.getName();
        Configuration.timeout = 15000;
        Configuration.browserSize = null;
        log("ENV: " + getEnv());
        log("PLATFORM: " + getPlatform());
        // skip the whole setup
        Selenide.open();
    }

    @AfterClass(alwaysRun = true)
    public void afterClassTearDown() {
        getDriver().quit();
    }

    public LoginScreen openLoginScreen() {
        return new LoginScreen();
    }
}
