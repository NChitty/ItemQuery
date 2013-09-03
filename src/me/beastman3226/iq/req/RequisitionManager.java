package me.beastman3226.iq.req;

import me.beastman3226.iq.utils.ItemConverter;
import me.beastman3226.iq.utils.PriceUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author beastman3226
 */
public class RequisitionManager {
    public static Requisition createReq(String[] items, Player p) {
        Requisition req = null;
        ItemStack[] is = ItemConverter.convert(items.length, items);
        double price = PriceUtil.calculate(is);
        req = new Requisition(is, p, price);
        return req;
    }
}
