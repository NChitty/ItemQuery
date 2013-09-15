package me.beastman3226.iq.database;

import me.beastman3226.iq.Main;
import me.beastman3226.iq.errors.DatabaseNotSupportedException;

/**
 *
 * @author beastman3226
 */
public enum DatabaseType {

    MySQL(Main.d.main.data, "MySQL"),
    SQLite(Main.d.main.data, "SQLite");

    private final Database db;
    private final String dbName;

    DatabaseType(Database db, String dbName) {
        if(dbName.equals("MySQL")) {
            this.db = (MySQL) db;
        } else {
            this.db = (SQLite) db;
        }
        this.dbName = dbName;
    }

    public Database getDatabase() throws DatabaseNotSupportedException {
        switch(this.dbName.toUpperCase()) {
            case "MYSQL" : {
                return (MySQL) this.db;
            }
            case "SQLITE" : {
                return (SQLite) this.db;
            }
            default : {
                throw new DatabaseNotSupportedException();
            }
        }
    }
}
