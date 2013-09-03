package me.beastman3226.iq;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;


/**
 *
 * @author beastman3226
 */
public class BuyableMaterials {

    static void initAll(Main aThis) {
        plugin = aThis;
        int i = 0;
        for(Material m : Material.values()) {
            BuyableMaterials n = new BuyableMaterials(m, plugin.getConfig().getDouble(m.name()), true, i);
            materialList[i] = n;
            i++;
        }
    }

    private final Material mat;   // in kilograms
    private double unitPrice; // in meters
    private final boolean buyable;
    private static Main plugin;
    private static BuyableMaterials[] materialList = new BuyableMaterials[Material.values().length];
    public BuyableMaterials(Material m, double price, boolean buyable, int index) {
        this.mat = m;
        this.unitPrice = price;
        this.buyable = buyable;
    }

    public double getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(double newPrice) {
        this.unitPrice = newPrice;
    }

    public static BuyableMaterials getMaterial(Material m) {
        BuyableMaterials s = null;
        for(BuyableMaterials bm : materialList) {
            if(bm.mat.equals(m)) {
                s = bm;
                return bm;
            }
        }
        return s;
    }

    public static BuyableMaterials getMaterial(int index) {
        return materialList[index];
    }

}
