package me.beastman3226.iq.data;

import java.sql.Connection;
import me.beastman3226.iq.Main;
import me.beastman3226.iq.database.DatabaseType;

/**
 *
 * @author beastman3226
 */
public abstract class Data {

    public Connection connect;
    public Main main;

    public Data(Main plugin) {
        this.main = plugin;
    }

    public abstract DatabaseType getType();
}
