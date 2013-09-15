package me.beastman3226.iq.data;

import me.beastman3226.iq.database.SQLite;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author beastman3226
 */
public class DataSQLite implements Data {

    private static SQLite dbS;
    protected SQLite db;

    public static DataSQLite initialize(Plugin p, String dbLocation) {
        dbS = new SQLite(p, dbLocation);
        return new DataSQLite();
    }

    @Override
    public SQLite getDatabase() {
        db = dbS;
        return db;
    }

}
