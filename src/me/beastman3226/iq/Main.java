package me.beastman3226.iq;

import java.util.HashMap;
import java.util.logging.Level;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author beastman3226
 */
public class Main extends JavaPlugin {

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
                getLogger().log(Level.INFO, "Please set the cost for {0}", mat.name());
            }
        }
        this.getConfig().options().header("MySQL is our preferred database type. SQLite is the only other supported type.");
    }

    @Override
    public void onDisable() {

    }
}
