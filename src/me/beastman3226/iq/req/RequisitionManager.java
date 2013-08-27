package me.beastman3226.iq.req;

import java.sql.Date;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author beastman3226
 */
public class RequisitionManager {
    public static Requisition createReq(String[] items, Player p) {
        Requisition req = null;
        ItemStack[] is = new ItemStack[items.length];
        req = new Requisition(is, p, price);
        return req;
    }
}
