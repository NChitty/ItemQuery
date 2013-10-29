package me.beastman3226.iq.req;

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
    }


}
