import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolImpl{
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    static {
        config.setDriverClassName("org.h2.Driver");
        config.setJdbcUrl("jdbc:h2:tcp://localhost/~/test");
        config.setUsername("sa");
        config.setPassword("");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
