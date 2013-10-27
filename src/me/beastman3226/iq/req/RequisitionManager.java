package me.beastman3226.iq.req;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.beastman3226.iq.Main;
import me.beastman3226.iq.errors.ItemFormatException;
import me.beastman3226.iq.utils.ItemConverter;
import me.beastman3226.iq.utils.PriceUtil;
import me.beastman3226.iq.utils.SQLScanner;
import org.bukkit.entity.Player;
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
        return req;
    }

    private static void store(Requisition req) {
        if(Main.db.checkConnection()) {
            Connection c = Main.db.getConnection();
            try {
                Statement s = c.createStatement();
                try {
                    s.execute(SQLScanner.storeReq(new File("/StoreReq.sql"), req.playerName, req.price, ItemConverter.convert(req.items)));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(RequisitionManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException ex) {
                Logger.getLogger(RequisitionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
