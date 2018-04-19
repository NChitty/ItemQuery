package me.beastman3226.iq.data;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.beastman3226.iq.ItemQuery;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author beastman3226
 */
public class FileHandler {

    private static File requisitionFile;
    public static FileConfiguration requisitionYaml;

    public static void initFiles(Plugin p) {
        requisitionFile = new File(p.getDataFolder(), "requisitions.yml");

        if (!requisitionFile.exists()) {
            requisitionFile.getParentFile().mkdirs();
            try {
                requisitionFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ItemQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        requisitionYaml = YamlConfiguration.loadConfiguration(requisitionFile);
    }

    /**
     * Reloads the specified config, simply dumps all information that is in
     * memory and replaces it with all information from file.
     *
     */
    public static void reload() {
        try {
            requisitionYaml.load(requisitionFile);
        } catch (FileNotFoundException ex) {
            ItemQuery.instance.getLogger().severe(ex.getLocalizedMessage());
        } catch (IOException ex) {
            ItemQuery.instance.getLogger().severe(ex.getLocalizedMessage());
        } catch (InvalidConfigurationException ex) {
            ItemQuery.instance.getLogger().severe(ex.getLocalizedMessage());
        }

    }

    /**
     * Saves all information in all the configs
     *
     */
    public static void save() {
        try {
            requisitionYaml.save(requisitionFile);
        } catch (IOException ex) {
            Logger.getLogger(ItemQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
