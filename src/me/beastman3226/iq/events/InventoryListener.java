package me.beastman3226.iq.events;

import me.beastman3226.iq.req.ItemInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;

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
            ii.getRequisition().update(ii.getItems());
        }
    }

}
