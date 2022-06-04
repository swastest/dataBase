package connectBD;

import config.DataBaseInterface;
import org.aeonbits.owner.ConfigFactory;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
// Подключение к БД по сингл-тону, удобнее это делать через енам класс (крутые пацаны в 2к22 году все так делают)
public enum DataSourceProvider {
    INSTANCE;

    private PGSimpleDataSource dataSource;

    public DataSource getDataSource() {
        DataBaseInterface config = ConfigFactory.create(DataBaseInterface.class);
        if (dataSource == null){
            dataSource = new PGSimpleDataSource();
            dataSource.setServerName(config.serverName());
            dataSource.setPortNumber(config.portNumber());
            dataSource.setUser(config.user());
            dataSource.setDatabaseName(config.databaseName());
            dataSource.setPassword(config.password());
        }

        return dataSource;
    }
}
