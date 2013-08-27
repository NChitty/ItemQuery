package me.beastman3226.iq;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;


/**
 *
 * @author beastman3226
 */
public enum BuyableMaterials {
    ACTIVATOR_RAIL (Material.ACTIVATOR_RAIL, 110.0, true),
    AIR (Material.AIR, 0.0, false),
    EARTH (Material.ANVIL, 6.37814e6, true),
    MARS (Material.APPLE, 3.3972e6, true),
    JUPITER (Material.ARROW,   7.1492e7, true),
    SATURN (Material.BAKED_POTATO, 6.0268e7, true),
    URANUS (Material.BEACON, 2.5559e7, true),
    NEPTUN (Material.BED, 2.4746e7, true);

    private final Material mat;   // in kilograms
    private double unitPrice; // in meters
    private final boolean buyable;
    private static final Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("ItemQuery");
    BuyableMaterials(Material m, double price, boolean buyable) {
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

}
