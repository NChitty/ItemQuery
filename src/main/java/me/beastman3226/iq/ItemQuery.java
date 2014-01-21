package me.beastman3226.iq;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.beastman3226.iq.db.MySQL;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author beastman3226
 */
public class ItemQuery extends JavaPlugin {


    public static ItemQuery instance;
    private MySQL database;
    public ItemQuery() {
        instance = this;
    }

    @Override
    public void onEnable() {
        if(!getConfig().contains("database")) {
            getConfig().options().copyDefaults(true);
        }
        database = new MySQL(this, getConfig().getString("database.ip"),
                getConfig().getString("database.port"),
                getConfig().getString("database.name"),
                getConfig().getString("database.user"),
                getConfig().getString("database.pass"));

    }

    public MySQL getMySQLDatabase() {
        if(database.checkConnection()) {
            return database;
        } else {
            database.openConnection();
            return database;
        }
    }

    public void setupTable() {
        if(!database.checkConnection()) {
            database.openConnection();
            try {
                Statement s = database.getConnection().createStatement();
            } catch (SQLException ex) {
                getLogger().log(Level.WARNING, ex.getMessage());
            }
        }
    }
}
