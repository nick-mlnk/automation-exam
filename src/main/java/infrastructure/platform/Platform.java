package infrastructure.platform;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.net.MalformedURLException;
import java.net.URL;

import static infrastructure.configuration.ConfigurationManger.getRemoteAddressUrl;
import static java.util.Objects.isNull;

@AllArgsConstructor
@Getter
public enum Platform {

    IOS("ios") {
        @Override
        public AppiumDriver getDriver() {
            IOSDriver driver = null;
            if (isNull(driver)) {
                try {
                    driver = new IOSDriver(new URL(getRemoteAddressUrl()), DesiredCaps.IOS.getCapabilities());
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
            return driver;
        }
    },

    ANDROID("android") {
        @Override
        public AppiumDriver getDriver() {
            AndroidDriver driver = null;
            if (isNull(driver)) {
                try {
                    driver = new AndroidDriver(new URL(getRemoteAddressUrl()), DesiredCaps.ANDROID.getCapabilities());
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
            return driver;
        }
    };
    final String name;

    public static Platform from(String string) {
        if (string != null) {
            String trimmed = string.trim();
            for (Platform candidate : Platform.values()) {
                if (trimmed.equalsIgnoreCase(candidate.name))
                    return candidate;
            }
        }
        throw new IllegalArgumentException("\nUnsupported platform: " + string + "\n");
    }

    public AppiumDriver getDriver() {
        throw new IllegalStateException("Please Override this method.");
    }
}
