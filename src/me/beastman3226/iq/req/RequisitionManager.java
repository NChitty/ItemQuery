package me.beastman3226.iq.req;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.beastman3226.iq.Main;
import me.beastman3226.iq.errors.ItemFormatException;
import me.beastman3226.iq.errors.NonExistantRequisitionException;
import me.beastman3226.iq.events.RequisitionMadeEvent;
import me.beastman3226.iq.utils.ItemConverter;
import me.beastman3226.iq.utils.PriceUtil;
import me.beastman3226.iq.utils.RequisitionMath;
import me.beastman3226.iq.utils.SQLScanner;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author beastman3226
 */
public class RequisitionManager {
    public static Requisition createReq(String[] items, Player p) {
        Requisition req = null;
        ItemStack[] is = null;
        try {
            is = ItemConverter.convert(items.length, items);
        } catch (ItemFormatException ex) {
            Logger.getLogger(RequisitionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        double price = PriceUtil.calculate(is);
        req = new Requisition(is, p.getName(), price);
        store(req);
        Bukkit.getServer().getPluginManager().callEvent(new RequisitionMadeEvent(req));
        return req;
    }

    private static void store(Requisition req) {
        if(Main.db.checkConnection()) {
            Connection c = Main.db.getConnection();
            try {
                Statement s = c.createStatement();
                try {
                    s.execute(SQLScanner.storeReq(new File("/StoreReq.sql"), req.player, req.price, ItemConverter.convert(req.items)));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(RequisitionManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException ex) {
                Logger.getLogger(RequisitionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static Requisition getReq(String player) throws NonExistantRequisitionException, ItemFormatException {
        Requisition req = null;
        try {
        ResultSet rs = Main.db.getStatment().executeQuery("SELECT * FROM 'requisitions' WHERE PlayerName=" + player);
            while(rs.next()) {
                if(rs.getString("PlayerName").equalsIgnoreCase(player)) {
                    req = new Requisition(ItemConverter.convert(rs.getString("ReqItems").split(",")), player, PriceUtil.calculate(ItemConverter.convert(rs.getString("ReqItems").split(","))));
                }
            }
        } catch (SQLException e) {
        }
        if(req == null) {
            throw new NonExistantRequisitionException();
        }
        return req;
    }

    public static boolean order(String player) {
        Requisition req = null;
        try {
            req = getReq(player);
        } catch (NonExistantRequisitionException | ItemFormatException ex) {
            Logger.getLogger(RequisitionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(Main.econ.has(player, req.price)) {
                Player p = Bukkit.getPlayerExact(player);
                ItemInventory i = new ItemInventory(player, req.items);
                i.showInventory();
                return true;
        }
        return false;
    }
}
