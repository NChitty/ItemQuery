package me.beastman3226.iq.utils;

import java.text.NumberFormat;
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
            s = s.replaceAll(" ", "");
            ItemStack toAdd = null;
            int amount = Integer.parseInt(s.split(";")[1]);
            Material mat;
            short data = Short.parseShort(s.split(";")[0].split(":")[1]);
            String _mat = s.split(";")[0].split(":")[0];
            try {
                int id = Integer.parseInt(_mat);
                mat = Material.getMaterial(id);
            } catch(NumberFormatException nfe) {
                mat = Material.getMaterial(_mat);
            }
            if(mat != null & amount != 0)
                toAdd = new ItemStack(mat, amount, data);
            if(toAdd != null) {
                System.out.println("ItemStack: " + toAdd.getType().toString() + ", " + toAdd.getAmount() + ", " + toAdd.getDurability());
                req.add(toAdd);
            }
        }
        return req.toArray(new ItemStack[]{});
    }

    public static String[] convert(ItemStack[] items) {
        ArrayList<String> toReturn = new ArrayList<String>();
        for (ItemStack item : items) {
            String toAdd = ' ' + item.getType().toString() + ":" + item.getDurability() + ";" + item.getAmount() + ' ';
            toReturn.add(toAdd);
        }
        return toReturn.toArray(new String[]{});
    }

    public static String[] convertToFile(String[] items) {
        ArrayList<String> req = new ArrayList<String>();
        for (String s : items) {
            String toAdd = null;
            int amount;
            Material mat;
            short data = 0;
            String _mat;
            if(s.contains(":")) {
                amount = Integer.parseInt(s.split(";")[1]);
                _mat = s.split(":")[0];
                data = Short.parseShort(s.split(":")[1].split(";")[0]);
            } else {
                amount = Integer.parseInt(s.split(";")[1]);
                _mat = s.split(";")[0];
            }
            try {
                int id = Integer.parseInt(_mat);
                mat = Material.getMaterial(id);
            } catch(NumberFormatException nfe) {
                mat = Material.getMaterial(_mat.toUpperCase());
            }
            if(mat != null & amount != 0)
                toAdd =  ' ' + mat.name() + ":" + data + ";" + amount + ' ';
            if(toAdd != null) {
                System.out.println("Adding " + toAdd);
                req.add(toAdd);
            }
        }
        return req.toArray(new String[]{});
    }

}
