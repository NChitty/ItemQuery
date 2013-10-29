package me.beastman3226.iq.req;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.beastman3226.iq.errors.ItemFormatException;
import me.beastman3226.iq.errors.NonExistantRequisitionException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author beastman3226
 */
public class ItemInventory {

    private HashSet<ItemStack> items = new HashSet<>();
    private Inventory showed;
    private static final int size = 54;
    private String player;
    protected static ArrayList<ItemInventory> inventories = new ArrayList<>();

    public ItemInventory(String player, ItemStack[] items) {
        this.player = player;
        this.setItems(items);
    }

    public Inventory showInventory() {
        showed = Bukkit.createInventory(Bukkit.getPlayerExact(player), size, "Here are your items");
        showed.setContents(this.getFirstFiftyFour());
        return showed;
    }

    public Inventory getInventory() {
        return showed;
    }

    public Player getPlayer() {
        return Bukkit.getPlayerExact(player);
    }

    public void removeItems(ItemStack[] items) {
        for(ItemStack item : items) {
            this.items.remove(item);
        }
    }

    public void removeItem(ItemStack item) {
        items.remove(item);
    }

    private void setItems(ItemStack[] items) {
        this.items.addAll(Arrays.asList(items));
    }

    public ItemStack[] getItems() {
        return this.items.toArray(new ItemStack[]{});
    }

    private ItemStack[] getFirstFiftyFour() {
        ItemStack[] is = new ItemStack[54];
        Iterator<ItemStack> isI = items.iterator();
        for(int i = 0; i < 54; i++) {
            try {
                is[i] = isI.next();
            } catch (NoSuchElementException e) {
                ItemStack[] newIS = new ItemStack[i];
                for(int j = 0; j < newIS.length; j++) {
                    newIS[j] = items.iterator().next();
                }
                is = newIS;
            }
        }
        return is;
    }

    public static ItemInventory getInventory(Player p) {
        ItemInventory ii = null;
        for(ItemInventory inv : inventories) {
            if(inv.getPlayer().equals(p)) {
                ii = inv;
                break;
            }
        }
        return ii;
    }

    public Requisition getRequisition() {
        try {
            return RequisitionManager.getReq(player);
        } catch (NonExistantRequisitionException | ItemFormatException ex) {
            Logger.getLogger(ItemInventory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
