package me.beastman3226.iq.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

import com.evilmidget.UUIDFetcher;
import me.beastman3226.iq.requisitions.RequisitionManager;
import me.beastman3226.iq.utils.Converter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author beastman3226
 */
public class CommandHandler implements CommandExecutor {

    public static HashMap<UUID, Inventory> playerMap = new HashMap<java.util.UUID, Inventory>();

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        if (cs instanceof Player) {
            if (cs.hasPermission(cmnd.getPermission())) {
                    if (cmnd.getName().equalsIgnoreCase("request") && strings.length > 0) {
                        cs.sendMessage(ChatColor.GREEN + "ItemQuery: " + ChatColor.WHITE + "Request put in.");
                        try {
                            return RequisitionManager.File.createRequisition(strings, (Player) cs);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (cmnd.getName().equalsIgnoreCase("retrieve")) {
                        int id = RequisitionManager.File.getRequisitionID(cs.getName());
                        if(id != 0) {
                        Inventory i = Bukkit.createInventory(((Player) cs), 54, "Requisition #" + id);
                        String[] vitems = RequisitionManager.File.getRequisition(cs.getName());
                        ItemStack[] items;
                        if (vitems != null) {
                            items = Converter.convert(vitems);
                        } else {
                            cs.sendMessage(ChatColor.RED + "ItemQuery: " + ChatColor.WHITE + "Could not retrieve items");
                            return false;
                        }
                        if (items.length > 54) {
                            ArrayList<ItemStack> enough = new ArrayList<ItemStack>(Arrays.asList(items));
                            i.addItem(enough.subList(0, 53).toArray(new ItemStack[]{}));
                            int k = 0;
                            while (k < 54) {
                                enough.remove(k);
                                k++;
                            }
                        } else {
                            i.setContents(items);
                        }
                        try {
                            playerMap.put(UUIDFetcher.getUUIDOf(cs.getName()), i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        ((Player) cs).openInventory(i);
                        return true;
                    } else {
                            cs.sendMessage(ChatColor.RED + "ItemQuery: " + ChatColor.WHITE + "You do not currently have an open request");
                            return false;
                        }
                    }
            } else {
                cs.sendMessage(ChatColor.translateAlternateColorCodes('&', cmnd.getPermissionMessage()));
                return false;
            }
        } else {
            cs.sendMessage("[ItemQuery]: I need a player name. Will be implementing admin functions soon");
            return false;
        }
        return false;
    }

}
