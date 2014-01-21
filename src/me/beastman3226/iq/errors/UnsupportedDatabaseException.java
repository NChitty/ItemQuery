package me.beastman3226.iq.errors;

/**
 *
 * @author beastman3226
 */
public class UnsupportedDatabaseException extends Exception {

    /**
     * Creates a new instance of
     * <code>UnsupportedDatabaseException</code> without detail message.
     */
    public UnsupportedDatabaseException() {
    }

    /**
     * Constructs an instance of
     * <code>UnsupportedDatabaseException</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public UnsupportedDatabaseException(String msg) {
        super(msg + " is not a supported database; Try MySQL or SQLite");
    }
}
