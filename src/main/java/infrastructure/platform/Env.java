package infrastructure.platform;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public enum Env {
    DEV("dev"),
    STAGE("stage"),
    PROD("prod"),
    SANDBOX("sandbox");

    final String name;

    public static Env from(String env) {
        if (Objects.nonNull(env)) {
            String trimmed = env.trim();
            for (Env candidate : Env.values()) {
                if (trimmed.equalsIgnoreCase(candidate.getName()))
                    return candidate;
            }
        }
        throw new IllegalArgumentException("\nUnsupported Env: " + env + "\n");
    }
}
