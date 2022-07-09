package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:properties/link.properties")
public interface LinkOwner extends Config {
    String url();
    String urlUi();
}
