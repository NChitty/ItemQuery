package me.beastman3226.iq.req;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.beastman3226.iq.Main;
import me.beastman3226.iq.events.RequisitionMadeEvent;
import me.beastman3226.iq.utils.PriceUtil;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author beastman3226
 */
public class Requisition {

    public ItemStack[] items;
    public double price;
    public String player;

    Requisition(ItemStack[] is, String name, double price) {
        items = is;
        player = name;
        this.price = price;
        Bukkit.getServer().getPluginManager().callEvent(new RequisitionMadeEvent(this));
    }

    public void update(ItemStack[] items) {
        this.items = items;
        this.price = PriceUtil.calculate(this.items);
    }

    public void trash() {
        this.items = null;
        this.price = 0.0;
        try {
            Main.db.getConnection().createStatement().execute("DELETE FROM 'requisitions' \n" +
                    "WHERE PlayerName='" + player + "'");
        } catch (SQLException ex) {
            Logger.getLogger(Requisition.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.player = null;
    }

}
