package me.beastman3226.iq;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.beastman3226.iq.commands.CommandHandler;
import me.beastman3226.iq.db.MySQL;
import me.beastman3226.iq.utils.SQLScanner;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Material;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author beastman3226
 */
public class Main extends JavaPlugin {

    public static MySQL db;
    public static Economy econ;
    private final CommandHandler ch = new CommandHandler();

    @Override
    public void onEnable() {
        if (!this.getConfig().contains("db.enabled")) {
            getConfig().set("db.enabled", true);
            getConfig().set("db.ip", "localhost");
            getConfig().set("db.port", "3306");
            getConfig().set("db.name", "minecraft");
            getConfig().set("db.user", "user");
            getConfig().set("db.pass", "password");
            this.saveConfig();
            for (Material mat : Material.values()) {
                this.getConfig().createSection(mat.name().toLowerCase());
                getLogger().log(Level.INFO, "Please set the cost for {0}", mat.name());
                this.saveConfig();
            }
        }
        if(this.getConfig().getBoolean("db.enabled")) {
            db = new MySQL(this,
                    getConfig().getString("db.ip"),
                    getConfig().getString("db.port"),
                    getConfig().getString("db.name"),
                    getConfig().getString("db.user"),
                    getConfig().getString("db.pass"));
        } else {
            System.out.println("Disabling ItemQuery, unable to establish MySQL connection");
            this.getServer().getPluginManager().disablePlugin(this);
        }
        if(db.checkConnection()) {
            try {
               SQLScanner.executeQuery(new File(getDataFolder(), "CreateTable.sql"));
            } catch (FileNotFoundException | SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        getCommand("request").setExecutor(ch);
        getCommand("redeem").setExecutor(ch);
        getCommand("retrieve").setExecutor(ch);
    }

    @Override
    public void onDisable() {

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
}
