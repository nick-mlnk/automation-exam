package infrastructure.platform;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum App {

    TUNEIN("us"),
    TUNEIN_ES("es", "&lang=es");

    private String region;
    private String domainUrl = "https://tunein.com/?abtestid=5015";

    App(String name, String version) {
        this.region = name;
        this.domainUrl = domainUrl.concat(version);
    }

    App(String name) {
        this.region = name;
        this.domainUrl = "https://tunein.com/?abtestid=5015";
    }

    public static App from(String region) {
        if (Objects.nonNull(region)) {
            String trimmed = region.trim();
            for (App candidate : App.values()) {
                if (trimmed.equalsIgnoreCase(candidate.getRegion()))
                    return candidate;
            }
        }
        throw new IllegalArgumentException("\nUnsupported App for region: " + region + "\n");
    }

}
