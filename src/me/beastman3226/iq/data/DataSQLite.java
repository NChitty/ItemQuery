package me.beastman3226.iq.data;

import me.beastman3226.iq.Main;
import me.beastman3226.iq.database.DatabaseType;

/**
 *
 * @author beastman3226
 */
public class DataSQLite extends Data {

    public DataSQLite(Main main) {
        super(main);
    }

    @Override
    public DatabaseType getType() {
        return DatabaseType.SQLite;
    }
}
