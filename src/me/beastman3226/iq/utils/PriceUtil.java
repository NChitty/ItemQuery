package me.beastman3226.iq.utils;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author beastman3226
 */
public class PriceUtil {

    private static Plugin p = Bukkit.getServer().getPluginManager().getPlugin("ItemQuery");

    public static double calculate(ItemStack[] is) {
        double price = 0.0;
        for(ItemStack item : is) {
            String path = item.getType().name().toLowerCase();
            price = price + p.getConfig().getDouble(path);
        }
        return 0;
    }

}
