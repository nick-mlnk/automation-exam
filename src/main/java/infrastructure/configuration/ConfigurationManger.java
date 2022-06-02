package infrastructure.configuration;

import infrastructure.platform.Browser;

import static java.lang.System.*;
import static java.util.Objects.nonNull;

public class ConfigurationManger {

    public static String DOMAIN_URL = "http://automationpractice.com/index.php"; // skip parametrization of env

    public static Browser getBrowser() {
        String name = nonNull(getProperty("browser")) ? getProperty("browser") : getenv("browser");
        name = nonNull(name) ? name.toLowerCase() : Browser.CHROME.getName();
        Browser browser = Browser.from(name);
        setProperty("browser", browser.getName());
        return browser;
    }

}
