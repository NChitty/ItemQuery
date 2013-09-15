package me.beastman3226.iq.req;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author beastman3226
 */
public class Requisition {

    private ItemStack[] items;
    private final Player reqPlayer;
    private final double price;

    protected Requisition(ItemStack[] items, Player req, double price) {
        this.items = items;
        this.reqPlayer = req;
        this.price = price;
    }

}
