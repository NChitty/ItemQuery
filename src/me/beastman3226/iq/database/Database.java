package me.beastman3226.iq.database;

import java.util.logging.Level;
import java.util.logging.Logger;
import me.beastman3226.iq.errors.DatabaseNotSupportedException;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author beastman3226
 */
public class Database {

    protected String host;
    protected String port;
    protected String dbName;
    protected String user;
    protected String pass;
    protected Plugin plugin;

    private void reconnect() throws DatabaseNotSupportedException {
        throw new DatabaseNotSupportedException();
    }

    private void createDatabase(String value) {
    }

    public static class DbBuilder {

        private String host = "localhost";
        private String port = "3306";
        private String dbName = "ItemQuery";
        private String user = "user";
        private String pass = "password";
        private Plugin plugin;

        public DbBuilder(String host, String port, Plugin plugin) {
            if (!host.equalsIgnoreCase("localhost")) {
                this.host = host;
            }
            if (!port.equalsIgnoreCase("3306")) {
                this.port = port;
            }
            this.plugin = plugin;
        }

        public DbBuilder dbName(String dbName) {
            if (!dbName.equalsIgnoreCase("ItemQuery")) {
                this.dbName = dbName;
            }
            return this;
        }

        public DbBuilder user(String user) {
            if (!user.equalsIgnoreCase("user")) {
                this.user = user;
            }
            return this;
        }

        public DbBuilder pass(String pass) {
            if (!pass.equalsIgnoreCase("password")) {
                this.pass = pass;
            }
            return this;
        }

        public Database build() {
            return new Database(this);
        }
    }

    public Database(DbBuilder builder) {
        this.host = builder.host;
        this.port = builder.port;
        this.dbName = builder.dbName;
        this.user = builder.user;
        this.pass = builder.pass;
        this.plugin = builder.plugin;
    }

    /**
     * Returns a string[] holding all of the databases values <p> Indexes
     * according to values: 0 = host 1 = port 2 = dbName 3 = user 4 = pass </p>
     *
     * @return Returns a String[] containing all the database types.
     */
    public String[] getProperties() {
        return new String[]{host, port, dbName, user, pass};
    }

    public void setProperty(String value, String property) {
        switch (property.toLowerCase()) {
            case "host": {
                this.host = value;
                try {
                    this.reconnect();
                } catch (DatabaseNotSupportedException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case "port": {
                this.port = value;
                try {
                    this.reconnect();
                } catch (DatabaseNotSupportedException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case "dbname": {
                this.dbName = value;
                this.createDatabase(value);
            }
            case "user": {
                this.user = value;
                try {
                    this.reconnect();
                } catch (DatabaseNotSupportedException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case "pass": {
                this.pass = value;
                try {
                    this.reconnect();
                } catch (DatabaseNotSupportedException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
