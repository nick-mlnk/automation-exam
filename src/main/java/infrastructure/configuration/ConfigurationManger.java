package infrastructure.configuration;

import infrastructure.platform.Browser;

import static java.lang.System.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class ConfigurationManger {

    public static String DOMAIN_URL = "http://automationpractice.com/index.php"; // skip parametrization of env
    public static String BROWSER_SYS_VAR = "browser";
    private static ConfigurationManger configurationManger;

    private ConfigurationManger() {
    }

    public static ConfigurationManger getInstance() {
        if (isNull(configurationManger)) {
            configurationManger = new ConfigurationManger();
        }
        return configurationManger;
    }

    public Browser getBrowser() {
        String name = nonNull(getProperty(BROWSER_SYS_VAR)) ? getProperty(BROWSER_SYS_VAR) : getenv(BROWSER_SYS_VAR);
        name = nonNull(name) ? name.toLowerCase() : Browser.CHROME.getName();
        Browser browser = Browser.from(name);
        setProperty(BROWSER_SYS_VAR, browser.getName());
        return browser;
    }

}
