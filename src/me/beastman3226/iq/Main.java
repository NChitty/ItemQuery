package me.beastman3226.iq;

import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author beastman3226
 */
public class Main extends JavaPlugin {


    @Override
    public void onEnable() {
        BuyableMaterials.initAll(this);
    }

    @Override
    public void onDisable() {

    }
}
