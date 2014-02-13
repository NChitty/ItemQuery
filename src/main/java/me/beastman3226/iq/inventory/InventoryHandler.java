package me.beastman3226.iq.inventory;

import me.beastman3226.iq.ItemQuery;
import me.beastman3226.iq.commands.CommandHandler;
import me.beastman3226.iq.requisitions.RequisitionManager;
import me.beastman3226.iq.utils.Pricing;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;

/**
 *
 * @author beastman3226
 */
public class InventoryHandler implements Listener {
    
    @EventHandler
    public void onClick(InventoryMoveItemEvent ide) {
        if(CommandHandler.playerMap.containsValue(ide.getSource()) && CommandHandler.playerMap.get(((Player) ide.getInitiator().getHolder()).getName()) == ide.getSource()) {
            Player p = (Player) ide.getInitiator().getHolder();
            double charge = Pricing.getPrice(ide.getItem());
            ItemQuery.econ.withdrawPlayer(p.getName(), charge);
            p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "ItemQuery" + ChatColor.GRAY + "]: " + ChatColor.WHITE + "You have been charged " + charge + ItemQuery.econ.currencyNamePlural() + " for purchasing " + ide.getItem().getType().name().toLowerCase());
            RequisitionManager.removeItem(ide.getItem(), p.getName());
        }
    }
    
}
