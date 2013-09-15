package me.beastman3226.iq.database;

import java.sql.Connection;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author beastman3226
 */
public abstract class WeirdDatabase extends Database{

    /**
     * Plugin instance, use for plugin.getDataFolder() and plugin.getLogger()
     */
    protected Plugin plugin;

    /**
     * Creates a new Database
     *
     * @param plugin
     *            Plugin instance
     */
    protected WeirdDatabase(Plugin plugin) {
        super(plugin);
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
}
