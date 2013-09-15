package me.beastman3226.iq.data;

import me.beastman3226.iq.database.Database;
import me.beastman3226.iq.database.MySQL;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author beastman3226
 */
public class DataMySQL implements Data {

    private static MySQL dbS;
    private MySQL db;

    public static DataMySQL initialize(Plugin p, String[] properties) {
        dbS = (MySQL) new MySQL.DbBuilder(properties[0], properties[1], p).dbName(properties[2]).user(properties[3]).pass(properties[4]).build();
        return new DataMySQL();
    }

    @Override
    public Database getDatabase() {
        db = dbS;
        return db;
    }

}
