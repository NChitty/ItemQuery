package me.beastman3226.iq.requisitions;

import java.util.*;

import com.evilmidget.UUIDFetcher;
import me.beastman3226.iq.data.FileHandler;
import me.beastman3226.iq.utils.Converter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author beastman3226
 */
public class RequisitionManager {

    public static class File {

        public static boolean createRequisition(String[] items, Player p) throws Exception {
            String[] convertedItems = Converter.convertToFile(items);
            FileHandler.requisitionYaml.set(UUIDFetcher.getUUIDOf(p.getName()).toString() + ".items", convertedItems);
            int id = createID();
            FileHandler.requisitionYaml.set(UUIDFetcher.getUUIDOf(p.getName()).toString() + ".id", id);
            FileHandler.save();
            return true;
        }

        private static int createID() {
            Random r = new Random();
            int id = (r.nextInt(1000000) + 1000);
            for(String s : FileHandler.requisitionYaml.getValues(false).keySet()) {
                if(FileHandler.requisitionYaml.getInt(s + ".id") == id) return createID();
            }
            return id;
        }

        public static int getRequisitionID(String name) {
            FileHandler.reload();
            try {
                return FileHandler.requisitionYaml.getInt(UUIDFetcher.getUUIDOf(name).toString() + ".id");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return -1;
        }

        public static String[] getRequisition(String name) {
            FileHandler.reload();
           String[] sample = new String[]{};
            try {
                List<String> list =  FileHandler.requisitionYaml.getStringList(UUIDFetcher.getUUIDOf(name).toString() + ".items");

                return list.toArray(sample);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        public static void removeItem(ItemStack item, String name) {
            List wack = Arrays.asList(Converter.convert(getRequisition(name)));
            ArrayList<ItemStack> items = new ArrayList<ItemStack>(wack);
            if (items.contains(item)) {
                ItemStack unmodified = items.remove(items.indexOf(item));
                unmodified.setAmount(unmodified.getAmount() - item.getAmount());
                if (unmodified.getAmount() > 0) {
                    items.add(unmodified);
                }
                try {
                    boolean hasItems = false;
                    for (ItemStack is : items) {
                        hasItems = is.getAmount() >= 0;
                    }
                    if (!hasItems || items.isEmpty()) {
                        FileHandler.requisitionYaml.set(UUIDFetcher.getUUIDOf(name).toString(), null);
                        FileHandler.save();
                        return;
                    }
                    FileHandler.requisitionYaml.set(UUIDFetcher.getUUIDOf(name).toString() + ".items", Converter.convert(items.toArray(new ItemStack[]{})));
                    FileHandler.save();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
