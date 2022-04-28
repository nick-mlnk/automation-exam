package infrastructure.platform;

import infrastructure.configuration.ConfigurationManger;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;

import static infrastructure.configuration.ConfigurationManger.isRemoteEnabled;

public enum DesiredCaps {

    IOS {
        @Override
        protected DesiredCapabilities getNativeDesiredCapabilities() {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("app", "appPath");
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
            // skip other caps setup
            return caps;
        }

        @Override
        protected DesiredCapabilities getRemoteDesiredCapabilities() {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("app", "appUrl");
            // skip other caps setup
            return caps;
        }
    },
    ANDROID {
        @Override
        protected DesiredCapabilities getNativeDesiredCapabilities() {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("app", "appPath");
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            // skip other caps setup
            return caps;
        }

        @Override
        protected DesiredCapabilities getRemoteDesiredCapabilities() {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("app", "appUrl");
            // skip other caps setup
            return caps;
        }
    };

    protected DesiredCapabilities getCapabilities() {
        return isRemoteEnabled() ? getRemoteDesiredCapabilities() : getNativeDesiredCapabilities();
    }

    protected DesiredCapabilities getNativeDesiredCapabilities() {
        throw new IllegalStateException("No Implementation for Capabilities were found. Override these capabilities.");
    }

    protected DesiredCapabilities getRemoteDesiredCapabilities() {
        throw new IllegalStateException("No Implementation for Capabilities were found. Override these capabilities.");
    }
}
