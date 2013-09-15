package me.beastman3226.iq.data;

import me.beastman3226.iq.Main;
import me.beastman3226.iq.database.DatabaseType;

/**
 *
 * @author beastman3226
 */
public class DataMySQL extends Data {

    public DataMySQL(Main m) {
        super(m);
    }

    @Override
    public DatabaseType getType() {
        return DatabaseType.MySQL;
    }

}
