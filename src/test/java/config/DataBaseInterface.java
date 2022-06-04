package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:properties/dataBase.properties")

public interface DataBaseInterface extends Config {
    String serverName();
    Integer portNumber();
    String user();
    String databaseName();
    String password();
}
