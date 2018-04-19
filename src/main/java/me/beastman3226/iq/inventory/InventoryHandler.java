package me.beastman3226.iq.inventory;

import com.evilmidget.UUIDFetcher;
import me.beastman3226.iq.ItemQuery;
import me.beastman3226.iq.commands.CommandHandler;
import me.beastman3226.iq.requisitions.RequisitionManager;
import me.beastman3226.iq.utils.Pricing;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 *
 * @author beastman3226
 */
public class InventoryHandler implements Listener {
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onClick(InventoryClickEvent ide) {
        try {
            if(CommandHandler.playerMap.get(UUIDFetcher.getUUIDOf(((Player) ide.getInventory().getHolder()).getName())).equals(ide.getInventory())) {
                    if(ide.getAction().equals(InventoryAction.PLACE_ALL) || ide.getAction().equals(InventoryAction.PLACE_ONE) || ide.getAction().equals(InventoryAction.PLACE_SOME)) {
                        Player p = (Player) ide.getInventory().getHolder();
                        double charge = Pricing.getPrice(ide.getCursor());
                        EconomyResponse er = ItemQuery.econ.withdrawPlayer(p.getName(), charge);
                        if (er.type == ResponseType.FAILURE) {
                            p.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "ItemQuery" + ChatColor.GRAY + "]: " + ChatColor.RED + er.errorMessage);
                            ide.setCancelled(true);
                            return;
                        }
                        p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "ItemQuery" + ChatColor.GRAY + "]: " + ChatColor.WHITE + "You have been charged " + charge + " " + ItemQuery.econ.currencyNamePlural() + " for purchasing " + ide.getCursor().getType().name().toLowerCase());
                        RequisitionManager.File.removeItem(ide.getCursor(), p.getName());
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
