package me.beastman3226.iq.utils;

import me.beastman3226.iq.ItemQuery;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author beastman3226
 */
public class Pricing {

    public static double getPrice(ItemStack[] items) {
        double price = 0.0;
        for(ItemStack is : items) {
            price = price + (is.getAmount() * ItemQuery.instance.getConfig().getDouble(is.getType().name().toLowerCase()));
        }
        return price;
    }
    
    public static double getPrice(ItemStack items) {
        double price = 0.0;
        return (price = items.getAmount() * ItemQuery.instance.getConfig().getDouble(items.getType().name().toLowerCase()));
    }
    
}
