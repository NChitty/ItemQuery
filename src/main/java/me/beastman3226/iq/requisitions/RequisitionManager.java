package me.beastman3226.iq.requisitions;

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

    public static boolean createRequisition(String[] items, Player p) {
        String req = "";
        int i = 0;
        for(String s : items) {
            if(i == 0) {
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
    
    public static String getRequisitionPlayer(int id) {
        return (String) Query.getInfo("PlayerName", "ReqID", id);
    }
    
    public static int getRequisitionID(String name) {
        return (Integer) Query.getInfo("ReqID", "PlayerName", name);
    }
    
    public static double getRequisitionPrice(int id) {
        return (Double) Query.getInfo("Price", "ReqID", id);
    }
    
    public static double getRequisitionPrice(String name) {
        return (Double) Query.getInfo("Price", "PlayerName", name);
    }
    
    public static String getRequisition(int id) {
        return (String) Query.getInfo("Requisition", "ReqID", id);
    }
    
    public static String getRequisition(String name) {
        return (String) Query.getInfo("Requistion", "PlayerName", name);
    }
    
    public static void removeItem(ItemStack item, String name) {
        
        ItemStack[] items = Converter.convert(getRequisition(name).split(","));
            for(int i = 0; i < items.length; i++) {
                if(items[i].getType() == item.getType()) {
                    items[i].setAmount(items[i].getAmount() - item.getAmount());
                    if(items[i].getAmount() <= 0) {
                        items[i] = null;
                    } else {
                        items[i] = items[i];
                    }
                }
            }
            ItemStack[] returnTo = new ItemStack[items.length - 1];
            for(int k = 0; k < items.length; k++) {
                if(items[k] == null) {
                    continue;
                } else {
                    returnTo[k] = items[k];
                }
            }
        Query.editIndex(new Data().addData("Requisition", Converter.convert(returnTo)), "PlayerName", name);
    }
    
    public static void removeItem(ItemStack item, int id) {
        
        ItemStack[] items = Converter.convert(getRequisition(id).split(","));
            for(int i = 0; i < items.length; i++) {
                if(items[i].getType() == item.getType()) {
                    items[i].setAmount(items[i].getAmount() - item.getAmount());
                    if(items[i].getAmount() <= 0) {
                        items[i] = null;
                    } else {
                        items[i] = items[i];
                    }
                }
            }
            ItemStack[] returnTo = new ItemStack[items.length - 1];
            for(int k = 0; k < items.length; k++) {
                if(items[k] == null) {
                    continue;
                } else {
                    returnTo[k] = items[k];
                }
            }
            Query.editIndex(new Data().addData("Requisition", Converter.convert(returnTo)), "ReqID", id);
    }
}
