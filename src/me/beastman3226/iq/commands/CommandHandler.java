package me.beastman3226.iq.commands;

import me.beastman3226.iq.req.ItemInventory;
import me.beastman3226.iq.req.RequisitionManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author beastman3226
 */
public class CommandHandler implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmnd, String string, String[] args) {
        if(args.length > 1 && !(sender instanceof ConsoleCommandSender)) {
            if(cmnd.getAliases().contains(string) | cmnd.getName().equalsIgnoreCase("request")) {
               RequisitionManager.createReq(args, (Player) sender);
               return true;
            }
        } else {
            if(cmnd.getAliases().contains(string) | cmnd.getName().equalsIgnoreCase("redeem")) {
                sender.sendMessage(ChatColor.RED + "Note that these items will no longer be retrievable upon a restart!");
                if(RequisitionManager.order(sender.getName())) {
                    sender.sendMessage(ChatColor.GREEN + "You have enough money to retrieve all items. Use /retrieve to retrieve further items.");
                } else {
                    sender.sendMessage(ChatColor.RED + "You can redeem your items later, you currently do not have enough money.");
                }
                return true;
            } else if (cmnd.getAliases().contains(string) | cmnd.getName().equalsIgnoreCase("retrieve")) {
                ItemInventory.getInventory((Player) sender).showInventory();
                return true;
            }
        }
        return false;
    }

}
