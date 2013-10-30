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
public class RequisitionUpdateEvent extends Event {
    private String player;
    private Requisition req;
    private ChangeType type;

    public RequisitionUpdateEvent(Requisition req, ChangeType type) {
        this.player = req.player;
        this.req = req;
        this.type = type;
    }

    public ChangeType getType() {
        return type;
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

    public static enum ChangeType {
        ITEMS,
        PRICE,
        CONCURRENT,
        BOTH,
        ALL;
    }
}
