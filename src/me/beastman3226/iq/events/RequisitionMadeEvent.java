package me.beastman3226.iq.events;

import me.beastman3226.iq.req.Requisition;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 *
 * @author beastman3226
 */
public class RequisitionMadeEvent extends Event {

    private String player;
    private Requisition req;

    public RequisitionMadeEvent(Requisition req) {
        this.player = req.player;
        this.req = req;
    }

    public Player getPlayer() {
        return Bukkit.getPlayerExact(player);
    }

    public Requisition getRequisition() {
         return this.req;
    }

    private static HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
