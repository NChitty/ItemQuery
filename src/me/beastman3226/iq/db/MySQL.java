/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.beastman3226.iq.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.beastman3226.iq.errors.UnsupportedDatabaseException;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author beastman3226
 */
public class MySQL extends Database {

    private final String user;
    private final String database;
    private final String password;
    private final String port;
    private final String hostname;
    private Connection connection;
    private Statement s;

    /**
     * Creates a new MySQL instance
     *
     * @param plugin Plugin instance
     * @param hostname Name of the host
     * @param portnmbr Port number
     * @param database Database name
     * @param username Username
     * @param password Password
     */
    public MySQL(Plugin plugin, String hostname, String port, String database, String username, String password) {
        super(plugin);
        this.hostname = hostname;
        this.port = port;
        this.database = database;
        this.user = username;
        this.password = password;
        this.connection = openConnection();
    }

    @Override
    public final Connection openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database, this.user, this.password);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return connection;
    }

    @Override
    public boolean checkConnection() {
        return connection != null;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                plugin.getLogger().log(Level.SEVERE, "Error closing the MySQL Connection!");
                System.out.println(e.getLocalizedMessage());
            }
        }
    }

    public ResultSet querySQL(String query) {

        Connection c = openConnection();
        Statement s = null;

        try {
            s = c.createStatement();
        } catch (SQLException e1) {
            System.out.println(e1.getLocalizedMessage());
        }

        ResultSet ret = null;

        try {
            ret = s.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
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
            System.out.println(e1.getLocalizedMessage());
        }

    }

    public Statement getStatment() throws SQLException {
        return this.s == null ? s = this.getConnection().createStatement() : s;
    }
}
