package me.beastman3226.iq.utils;

import me.beastman3226.iq.errors.ItemFormatException;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author beastman3226
 */
public class ItemConverter {

    public static ItemStack[] convert(int length, String[] list) throws ItemFormatException {
        ItemStack[] is = new ItemStack[length];
        int index = 0;
        for(String item : list) {
            if(item.contains("#")) {
                String[] amount = item.split("#", 1);
                int itemAmount = 1;
                try {
                     itemAmount = Integer.parseInt(amount[1]);
                } catch (NumberFormatException nfe) {
                    throw new ItemFormatException(list[index]);
                } finally {
                    try {
                        is[index] = new ItemStack(getMaterialFromId(Integer.parseInt(amount[0])), itemAmount);
                    } catch (NumberFormatException nfe) {
                        is[index] = new ItemStack(getMaterialFromName(amount[0]), itemAmount);
                    }
                }
            } else {
                try {
                   is[index] = new ItemStack(getMaterialFromId(Integer.parseInt(item)), 1);
                   continue;
                } catch (NumberFormatException nfe) {
                   is[index] = new ItemStack(getMaterialFromName(item), 1);
                   continue;
                }
            }
            index++;
        }
        return is;
    }

    private static Material getMaterialFromId(int id) {
        Material mat = null;
        for(Material m : Material.values()) {
            if(m.getId() == id) {
                mat = m;
                return m;
            }
        }
        return mat;
    }

    private static Material getMaterialFromName(String name) {
        Material mat = null;
        for(Material m : Material.values()) {
            if(m.name().toLowerCase().equals(name)) {
                mat = m;
                return m;
            }
        }
        return mat;
    }

}
