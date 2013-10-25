package me.beastman3226.iq.db;

import java.sql.Connection;
import java.sql.Statement;
import me.beastman3226.iq.errors.UnsupportedDatabaseException;
import org.bukkit.plugin.Plugin;

/**
 * Abstract Database class, serves as a base for any connection method (MySQL, SQLite, etc.)
 *
 * @author -_Husky_-
 * @author tips48
 */
public abstract class Database {

    /**
     * Plugin instance, use for plugin.getDataFolder() and plugin.getLogger()
     */
    protected Plugin plugin;
    protected Statement statement;
    protected DatabaseType type;

    /**
     * Creates a new Database
     *
     * @param plugin
     *            Plugin instance
     */
    protected Database(Plugin plugin, DatabaseType type) {
        this.plugin = plugin;
        this.type = type;
    }

    /**
     * Opens a connection with the database
     *
     * @return Connection opened
     */
    public abstract Connection openConnection();

    /**
     * Checks if a connection is open with the database
     *
     * @return true if a connection is open
     */
    public abstract boolean checkConnection();

    /**
     * Gets the connection with the database
     *
     * @return Connection with the database, null if none
     */
    public abstract Connection getConnection();

    /**
     * Closes the connection with the database
     */
    public abstract void closeConnection();

    public abstract DatabaseType getType();

    public void setType(String name) throws UnsupportedDatabaseException {
        for(DatabaseType leType : DatabaseType.values()) {
            if(leType.getType().equals(name)) {
                this.type = leType;
            }
        }
        if (this.type == null) {
            throw new UnsupportedDatabaseException(name);
        }
    }
}
