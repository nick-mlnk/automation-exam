package web.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

    public WebDriver createDriver(String name) {
        if (name.equalsIgnoreCase("chrome")) {
            return new ChromeDriver();
        }
        if (name.equalsIgnoreCase("safari")) {
            return new SafariDriver();
        }
        // TODO other drivers creation
        throw new IllegalArgumentException("Unknown driver: " + name);
    }
}
