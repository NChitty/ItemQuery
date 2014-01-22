package me.beastman3226.iq.requisitions;

import me.beastman3226.iq.data.Query;
import me.beastman3226.iq.data.Query.Data;
import org.bukkit.entity.Player;

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
                .addData("Price", i));
        return true;
    }

}
