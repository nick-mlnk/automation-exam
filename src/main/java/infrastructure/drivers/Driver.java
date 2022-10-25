package infrastructure.drivers;

import com.codeborne.selenide.SelenideDriver;
import infrastructure.configuration.ConfigurationManger;
import infrastructure.platform.Browser;

import static java.util.Objects.isNull;

public class Driver {

    private static ThreadLocal<SelenideDriver> driverThreadLocal = new ThreadLocal<>();

    public static void setSelenideDriver(String browser) {
        driverThreadLocal.set(Browser.from(browser).getSelenideDriver());
    }

    public static SelenideDriver getSelenideDriver() {
        if (isNull(driverThreadLocal.get())) {
            setSelenideDriver(ConfigurationManger.getInstance().getBrowser().getName());
        }
        return driverThreadLocal.get();
    }

}
