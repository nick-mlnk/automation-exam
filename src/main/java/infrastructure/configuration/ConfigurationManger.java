package infrastructure.configuration;

import infrastructure.platform.App;
import infrastructure.platform.Env;

import static java.lang.System.*;
import static java.util.Objects.nonNull;

public class ConfigurationManger {

    public static Env getEnv() {
        String env = getProperty("env");
        env = nonNull(env) ? env.toLowerCase() : Env.DEV.getName();
        Env environment = Env.from(env);
        setProperty("env", environment.getName());
        return environment;
    }

    public static App getApp() {
        String region = nonNull(getProperty("app")) ? getProperty("app") : getenv("app");
        region = nonNull(region) ? region.toLowerCase() : App.TUNEIN.getDomainUrl();
        App application = App.from(region);
        setProperty("app", application.getRegion());
        return application;
    }

}
