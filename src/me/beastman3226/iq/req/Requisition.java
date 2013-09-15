package me.beastman3226.iq.req;

import org.bukkit.inventory.ItemStack;

/**
 *
 * @author beastman3226
 */
public class Requisition {

    public ItemStack[] items;
    public String playerName;
    public double price;

    public Requisition(ItemStack[] is, String name, double price) {
        this.items = is;
        this.playerName = name;
        this.price = price;
    }

}
