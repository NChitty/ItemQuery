package me.beastman3226.iq.inventory;

import me.beastman3226.iq.ItemQuery;
import me.beastman3226.iq.commands.CommandHandler;
import me.beastman3226.iq.requisitions.RequisitionManager;
import me.beastman3226.iq.utils.Pricing;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;
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
            EconomyResponse er = ItemQuery.econ.withdrawPlayer(p.getName(), charge);
            if(er.type == ResponseType.FAILURE) {
                p.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "ItemQuery" + ChatColor.GRAY + "]: " + ChatColor.RED + er.errorMessage);
                ide.setCancelled(true);
                return;
            }
            p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "ItemQuery" + ChatColor.GRAY + "]: " + ChatColor.WHITE + "You have been charged " + charge + ItemQuery.econ.currencyNamePlural() + " for purchasing " + ide.getItem().getType().name().toLowerCase());
            RequisitionManager.Database.removeItem(ide.getItem(), p.getName());
        }
    }
    
}
