package me.beastman3226.iq;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import me.beastman3226.iq.commands.CommandHandler;
import me.beastman3226.iq.db.MySQL;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Material;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author beastman3226
 */
public class ItemQuery extends JavaPlugin {


    public static ItemQuery instance;
    private MySQL database;
    public static Economy econ;
    public ItemQuery() {
        instance = this;
    }

    @Override
    public void onEnable() {
        if(!getConfig().contains("database")) {
            this.defaults();
            getConfig().options().copyDefaults(true);
        }
        database = new MySQL(this, getConfig().getString("database.ip"),
                getConfig().getString("database.port"),
                getConfig().getString("database.name"),
                getConfig().getString("database.user"),
                getConfig().getString("database.pass"));
        setupTable();
        setupEconomy();
        getCommand("request").setExecutor(new CommandHandler());
        getCommand("retrieve").setExecutor(new CommandHandler());
    }

    @Override
    public void onDisable() {
        this.saveConfig();
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
                s.executeQuery("IF OBJECT_ID('" + database.getName() + "requisitions') IS NULL" + "\n"
                        + "CREATE TABLE(ReqID INTEGER NOT NULL AUTO_INCREMENT, PlayerName VARCHAR(255), Requisition Text, Price FLOAT, PRIMARY KEY (ReqID));");
            } catch (SQLException ex) {
                getLogger().log(Level.WARNING, ex.getMessage());
            }
        }
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private void defaults() {
        HashMap<String, Object> defaults = new HashMap<String, Object>();
        defaults.put("database.ip", "change");
        defaults.put("database.port", "change");
        defaults.put("database.name", "change");
        defaults.put("database.user", "change");
        defaults.put("database.pass", "change");
        for(Material m : Material.values()) {
            defaults.put(m.name().toLowerCase(), 0.0);
        }
        getConfig().addDefaults(defaults);
        getLogger().log(Level.WARNING, "The defaults have been set! You should configure your config.yml");
    }

}
