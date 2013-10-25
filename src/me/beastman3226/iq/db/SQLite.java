/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.beastman3226.iq.db;


import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.beastman3226.iq.errors.UnsupportedDatabaseException;
import org.bukkit.plugin.Plugin;


/**
 * Connects to and uses a SQLite database
 *
 * @author tips48
 */
public class SQLite extends Database {
    private final String dbLocation;

    private Connection connection;

    /**
     * Creates a new SQLite instance
     *
     * @param plugin
     *            Plugin instance
     * @param dbLocation
     *            Location of the Database (Must end in .db)
     */
    public SQLite(Plugin plugin, String dbLocation) {
        super(plugin, DatabaseType.SQLite);
        this.dbLocation = dbLocation;
        this.connection = null;
        try {
            super.setType("SQLite");
        } catch (UnsupportedDatabaseException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Connection openConnection() {
        File file = new File(dbLocation);
        if (!(file.exists())) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "Unable to create database!");
            }
        }
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + plugin.getDataFolder().toPath().toString() + "/" + dbLocation);
        } catch (SQLException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not connect to SQLite server! because: {0}", e.getMessage());
        } catch (ClassNotFoundException e) {
            plugin.getLogger().log(Level.SEVERE, "JDBC Driver not found!");
        }
        return connection;
    }

    @Override
    public boolean checkConnection() {
        try {
            return !(connection.isClosed());
        } catch (SQLException e) {
            return false;
        }
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
                plugin.getLogger().log(Level.SEVERE, "Error closing the SQLite Connection!");
            }
        }
    }

    @Override
    public DatabaseType getType() {
        return super.type;
    }

}
