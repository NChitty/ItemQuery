package me.beastman3226.iq.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author beastman3226
 */
public abstract class MySQL extends Database {

    private Connection connection;

    public MySQL(MySQL.DbBuilder builder) {
        super(builder);
    }

    public Connection openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dbName, this.user, this.pass);
        } catch (SQLException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not connect to MySQL server! because: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            plugin.getLogger().log(Level.SEVERE, "JDBC Driver not found!");
        }
        return connection;
    }

    public boolean checkConnection() {
        return connection != null;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                plugin.getLogger().log(Level.SEVERE, "Error closing the MySQL Connection!");
                e.printStackTrace();
            }
        }
    }

    public ResultSet querySQL(String query) {

        Connection c = openConnection();
        Statement s = null;

        try {
            s = c.createStatement();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        ResultSet ret = null;

        try {
            ret = s.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }

    public void updateSQL(String update) {

        Connection c = openConnection();
        Statement s = null;

        try {
            s = c.createStatement();
            s.executeUpdate(update);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    public static class DbBuilder extends Database.DbBuilder {

        private String host = "localhost";
        private String port = "3306";
        private String dbName = "ItemQuery";
        private String user = "user";
        private String pass = "password";
        private Plugin plugin;

        public DbBuilder(String host, String port, Plugin plugin) {
            super(host, port, plugin);
        }

        @Override
        public Database.DbBuilder dbName(String dbName) {
            if (!dbName.equalsIgnoreCase("ItemQuery")) {
                this.dbName = dbName;
            }
            return this;
        }

        @Override
        public Database.DbBuilder user(String user) {
            if (!user.equalsIgnoreCase("user")) {
                this.user = user;
            }
            return this;
        }

        @Override
        public Database.DbBuilder pass(String pass) {
            if (!pass.equalsIgnoreCase("password")) {
                this.pass = pass;
            }
            return this;
        }

        @Override
        public Database build() {
            return new Database(this);
        }
    }

}