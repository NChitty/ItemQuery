package me.beastman3226.iq.utils;

import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author beastman3226
 */
public class Converter {

    public static ItemStack[] convert(String[] items) {
        ArrayList<ItemStack> req = new ArrayList<ItemStack>();
        for (String s : items) {
            int id = 0;
            ItemStack toAdd;
            int amount = 1;
            String[] itemAmount = new String[2];
            short metadata = 0;
            if (s.contains(":")) {
                String[] meta = s.split(":");
                if (s.contains(";")) {
                    itemAmount = s.split(";");
                    try {
                        amount = Integer.parseInt(itemAmount[1]);
                    } catch (NumberFormatException nfe) {
                        amount = 1;
                    }
                }
                try {
                    id = Integer.parseInt(meta[0]);
                } catch (NumberFormatException nfe) {
                    id = Material.getMaterial(meta[0]).getId();
                }
                try {
                    metadata = Short.parseShort(meta[1]);
                } catch (NumberFormatException nfe) {
                    metadata = 0;
                }
            } else if(s.contains(";")){
                    itemAmount = s.split(";");
                    try {
                        id = Integer.parseInt(itemAmount[0]);
                    } catch (NumberFormatException nfe) {
                        id = Material.getMaterial(itemAmount[0]).getId();
                    }
                    try {
                        amount = Integer.parseInt(itemAmount[1]);
                    } catch (NumberFormatException nfe) {
                        amount = 1;
                    }
            } else {
                try {
                    id = Integer.parseInt(itemAmount[0]);
                } catch (NumberFormatException nfe) {
                    id = Material.getMaterial(itemAmount[0]).getId();
                }
                amount = 1;
            }
            if (id != 0) {
                toAdd = new ItemStack(id, amount, metadata);
                req.add(toAdd);
            } else {
                continue;
            }
        }
        return req.toArray(new ItemStack[]{});
    }

    public static String[] convert(ItemStack[] items) {
        ArrayList<String> toReturn = new ArrayList<String>();
        for (ItemStack item : items) {
            String toAdd;
            if (item.getDurability() != 0) {
                toAdd = item.getType().toString() + ":" + item.getDurability() + ";" + item.getAmount();
            } else {
                toAdd =item.getType().toString() + ";" + item.getAmount();
            }
            toReturn.add(toAdd);
        }
        return toReturn.toArray(new String[]{});
    }

}
