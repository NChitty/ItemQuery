package me.beastman3226.iq.requisitions;

import me.beastman3226.iq.data.FileHandler;
import me.beastman3226.iq.data.Query;
import me.beastman3226.iq.data.Query.Data;
import me.beastman3226.iq.utils.Converter;
import me.beastman3226.iq.utils.Pricing;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author beastman3226
 */
public class RequisitionManager {
    
    public static class File {
        public static boolean createRequisition(String[] items, Player p) {
            FileHandler.requisitionYaml.set(p.getName() + ".items", items);
            double price = Pricing.getPrice(Converter.convert(items));
            FileHandler.requisitionYaml.set(p.getName() + ".price", price);
            FileHandler.save();
            return true;
        }
        
        public static double getRequisitionPrice(String name) {
           return FileHandler.requisitionYaml.getDouble(name + ".price");
        }
        
        public static String[] getRequisition(String name) {
            return (String[]) FileHandler.requisitionYaml.getStringList(name + ".items").toArray();
        }
        
        public static void removeItem(ItemStack item, String name) {
            ItemStack[] items = Converter.convert(getRequisition(name));
            for(ItemStack match : items) {
                if(match.getType().equals(item.getType())) {
                    match.setAmount(match.getAmount() - item.getAmount());
                    if(match.getAmount() <=0) {
                        match = null;
                    }
                }
            }
            ItemStack[] returnTo = new ItemStack[items.length -1];
            for (int k = 0; k < items.length; k++) {
                if (items[k] == null) {
                    continue;
                } else {
                    returnTo[k] = items[k];
                }
            }
            FileHandler.requisitionYaml.set(name + ".items", Converter.convert(returnTo));
        }
    }
    
    
    public static class Database {
        public static boolean createRequisition(String[] items, Player p) {
            String req = "";
            int i = 0;
            for (String s : items) {
                if (i == 0) {
                    req = s;
                    i++;
                } else {
                    req = req + "," + s;
                }
            }
            Query.addIndex(new Data().addData("PlayerName", p.getName())
                    .addData("Requisition", req)
                    .addData("Price", Pricing.getPrice(Converter.convert(items))));
            return true;
        }

        public static int getRequisitionID(String name) {
            return (Integer) Query.getInfo("ReqID", "PlayerName", name);
        }

        public static double getRequisitionPrice(String name) {
            return (Double) Query.getInfo("Price", "PlayerName", name);
        }

        public static String getRequisition(String name) {
            return (String) Query.getInfo("Requistion", "PlayerName", name);
        }

        public static void removeItem(ItemStack item, String name) {

            ItemStack[] items = Converter.convert(getRequisition(name).split(","));
            for (int i = 0; i < items.length; i++) {
                if (items[i].getType() == item.getType()) {
                    items[i].setAmount(items[i].getAmount() - item.getAmount());
                    if (items[i].getAmount() <= 0) {
                        items[i] = null;
                    } else {
                        items[i] = items[i];
                    }
                }
            }
            ItemStack[] returnTo = new ItemStack[items.length - 1];
            for (int k = 0; k < items.length; k++) {
                if (items[k] == null) {
                    continue;
                } else {
                    returnTo[k] = items[k];
                }
            }
            Query.editIndex(new Data().addData("Requisition", Converter.convert(returnTo)), "PlayerName", name);
        }
    }
}
