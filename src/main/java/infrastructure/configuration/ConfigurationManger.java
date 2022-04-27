package infrastructure.configuration;

import infrastructure.platform.Env;
import infrastructure.platform.Platform;

import java.util.Objects;

import static infrastructure.platform.Platform.ANDROID;
import static java.lang.Boolean.parseBoolean;

public class ConfigurationManger {

    public static Platform getPlatform() {
        String platformName = System.getProperty("platform");
        platformName = Objects.nonNull(platformName) ? platformName : ANDROID.getName();
        Platform platform = Platform.from(platformName);
        System.setProperty("platform", platform.toString());
        return platform;
    }

    public static Env getEnv() {
        String env = System.getProperty("env");
        env = Objects.nonNull(env) ? env.toLowerCase() : Env.DEV.getName();
        Env environment = Env.from(env);
        System.setProperty("env", environment.getName());
        return environment;
    }
    public static boolean isRemoteEnabled() {
        return parseBoolean(System.getProperty("isRemoteEnabled"));
    }

    public static String getRemoteAddressUrl() {
        return isRemoteEnabled() ? "REMOTE_SERVICE_URL" : "APPIUM_URL";
    }
}
