package de.scheuraa.dunegonsv2.database;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import de.scheuraa.dunegonsv2.DungeonsPlugin;
import de.scheuraa.dunegonsv2.utils.Var;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

public class MySQLConnector {

    @Getter
    private static MysqlDataSource dataSource;

    public MySQLConnector() {
        Var.setDataSource(new MysqlConnectionPoolDataSource());
        dataSource = Var.getDataSource();
        FileConfiguration config = Var.getConfigManager().getConfig();
        dataSource.setServerName(config.getString("host"));
        dataSource.setPortNumber(config.getInt("port"));
        dataSource.setDatabaseName(config.getString("database"));
        dataSource.setUser(config.getString("user"));
        dataSource.setPassword(config.getString("password"));

        try {
            initDb();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    private void initDb() throws SQLException, IOException {
        String setup;
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("dbsetup.sql")) {
            assert in != null;
            setup = new String(in.readAllBytes());
        } catch (IOException e) {
            DungeonsPlugin.getDungeonsPlugin().getLogger().log(Level.SEVERE, "Could not read db setup file.", e);
            throw e;
        }
        String[] queries = setup.split(";");
        // execute each query to the database.
        for (String query : queries) {
            // If you use the legacy way you have to check for empty queries here.
            if (query.isBlank()) continue;
            try (Connection conn = dataSource.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.execute();
            }
        }
        DungeonsPlugin.getDungeonsPlugin().getLogger().info("Database setup complete.");
        Var.setDataSource(dataSource);
    }

}
