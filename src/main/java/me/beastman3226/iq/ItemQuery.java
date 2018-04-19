package me.beastman3226.iq;

import me.beastman3226.iq.commands.CommandHandler;
import me.beastman3226.iq.data.FileHandler;
import me.beastman3226.iq.inventory.InventoryHandler;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;

/**
 *
 * @author beastman3226
 */
public class ItemQuery extends JavaPlugin {


    public static ItemQuery instance;
    public static Economy econ;

    @Override
    public void onEnable() {
        instance = this;
        this.reloadConfig();
        if(!getConfig().contains("dirt")) {
            this.defaults();
            getConfig().options().copyDefaults(true);
        }
        this.saveConfig();
        FileHandler.initFiles(this);
        FileHandler.reload();
        
        setupEconomy();
        getCommand("request").setExecutor(new CommandHandler());
        getCommand("retrieve").setExecutor(new CommandHandler());
        getServer().getPluginManager().registerEvents(new InventoryHandler(), this);
        for(String s : FileHandler.requisitionYaml.getValues(true).keySet()) {
            System.out.println(s + ": " + FileHandler.requisitionYaml.getValues(true).get(s));
        }
    }

    @Override
    public void onDisable() {
        this.saveConfig();
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
        for(Material m : Material.values()) {
            if(m.isBlock()) {
                defaults.put(m.name().toLowerCase(), 0.0);
            }
        }
        getConfig().addDefaults(defaults);
        getLogger().log(Level.WARNING, "The defaults have been set! You should configure your config.yml");
    }

}
