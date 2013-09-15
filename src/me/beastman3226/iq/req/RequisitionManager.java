package me.beastman3226.iq.req;

import java.util.logging.Level;
import java.util.logging.Logger;
import me.beastman3226.iq.errors.ItemFormatException;
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
        ItemStack[] is = null;
        try {
            is = ItemConverter.convert(items.length, items);
        } catch (ItemFormatException ex) {
            Logger.getLogger(RequisitionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        double price = PriceUtil.calculate(is);
        return req;
    }
}
