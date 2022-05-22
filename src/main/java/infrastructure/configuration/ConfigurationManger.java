package infrastructure.configuration;

import infrastructure.platform.Env;

import java.util.Objects;

import static java.lang.Boolean.parseBoolean;

public class ConfigurationManger {

    public static Env getEnv() {
        String env = System.getProperty("env");
        env = Objects.nonNull(env) ? env.toLowerCase() : Env.DEV.getName();
        Env environment = Env.from(env);
        System.setProperty("env", environment.getName());
        return environment;
    }

}
