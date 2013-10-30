package me.beastman3226.iq.events;

import me.beastman3226.iq.Main;
import me.beastman3226.iq.errors.NoItemsToGiveException;
import me.beastman3226.iq.req.ItemInventory;
import me.beastman3226.iq.utils.PriceUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author beastman3226
 */
public class InventoryListener implements Listener {

    @EventHandler
    public void onInvEdit(InventoryMoveItemEvent e) {
        if(e.getInitiator().equals(ItemInventory.getInventory((Player) e.getDestination().getHolder()))) {
            ItemInventory ii = ItemInventory.getInventory((Player) e.getDestination().getHolder());
            ii.removeItem(e.getItem());
            try {
                ii.getRequisition().update(ii.getItems());
            } catch (NoItemsToGiveException ex) {
                ItemInventory.inventories.remove(ii);
                ii.getRequisition().trash();
            }
            Main.econ.withdrawPlayer(ii.getRequisition().player, PriceUtil.calculate(new ItemStack[]{e.getItem()}));
            ii.getPlayer().sendMessage(ChatColor.GREEN + "You payed " + ChatColor.GOLD + PriceUtil.calculate(new ItemStack[]{e.getItem()}) + Main.econ.currencyNamePlural() + ChatColor.GREEN+ " for " + e.getItem().getAmount() + " of " + e.getItem().getType().name());
        }
    }

}
