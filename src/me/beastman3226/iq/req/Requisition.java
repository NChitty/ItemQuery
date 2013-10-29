package me.beastman3226.iq.req;

import me.beastman3226.iq.events.RequisitionMadeEvent;
import me.beastman3226.iq.utils.PriceUtil;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author beastman3226
 */
public class Requisition {

    public ItemStack[] items;
    public double price;
    public String player;

    Requisition(ItemStack[] is, String name, double price) {
        items = is;
        player = name;
        this.price = price;
        Bukkit.getServer().getPluginManager().callEvent(new RequisitionMadeEvent(this));
    }

    public void update(ItemStack[] items) {
        this.items = items;
        this.price = PriceUtil.calculate(this.items);
    }

}
