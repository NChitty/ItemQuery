package me.beastman3226.iq.req;

import net.minecraft.server.v1_6_R2.ItemStack;
import org.bukkit.entity.Player;

/**
 *
 * @author beastman3226
 */
public class Requisition {

    private ItemStack[] items;
    private final Player reqPlayer;
    private final double price;

    private Requisition(ItemStack[] items, Player req, double price) {
        this.items = items;
        this.reqPlayer = req;
        this.price = price;
    }

}
