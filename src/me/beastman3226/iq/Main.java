package me.beastman3226.iq;

import java.util.HashMap;
import me.beastman3226.iq.data.Data;
import me.beastman3226.iq.data.DataSQLite;
import me.beastman3226.iq.database.Database;
import me.beastman3226.iq.database.MySQL;
import me.beastman3226.iq.database.SQLite;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author beastman3226
 */
public class Main extends JavaPlugin {

    public Database data;
    public static Data d;

    @Override
    public void onEnable() {
        HashMap<String, Object> defaults = new HashMap();
        defaults.put("db.enabled", true);
        defaults.put("db.type", "MySQL");
        defaults.put("db.ip", "localhost");
        defaults.put("db.port", "3306");
        defaults.put("db.name", "ItemQuery");
        defaults.put("db.user", "user");
        defaults.put("db.pass", "password");
        defaults.put("db.requisition.tableName", "Requisition");
        this.getConfig().addDefaults(defaults);
        if(!this.getConfig().contains("db.enabled")) {
            this.getConfig().setDefaults(this.getConfig().getDefaults());
            for(Material mat : Material.values()) {
                this.getConfig().createSection(mat.name().toLowerCase());
                getLogger().info("Please set the cost for " + mat.name());
            }
        }
        if(this.getConfig().getBoolean("db.enabled")) {
            switch(this.getConfig().getString("db.type").toLowerCase()) {
                case "sqlite" :
                    data = (SQLite) new SQLite(this, this.getConfig().getString("db.name"));
                    d = (DataSQLite) new DataSQLite(this);
                    break;
                case "mysql" :
                    data = (MySQL) new MySQL.DbBuilder(this.getConfig().getString("db.ip"), this.getConfig().getString("db.port"), this).dbName(this.getConfig().getString("db.name")).user(this.getConfig().getString("db.user")).pass(this.getConfig().getString("db.pass")).build();

                    break;
                default :
                    getLogger().info("That database isn't supported, please try SQLite or MySQL.");
                    break;
            }
        } else {
            getLogger().info("Shutting down ItemQuery, databases are essential.");
            this.getPluginLoader().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {

    }
}
