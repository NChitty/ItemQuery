/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.beastman3226.iq.errors;

/**
 *
 * @author Nicholas
 */
public class ItemFormatException extends Exception {

    /**
     * Creates a new instance of
     * <code>ItemFormatException</code> without detail message.
     */
    public ItemFormatException() {
    }

    /**
     * Constructs an instance of
     * <code>ItemFormatException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ItemFormatException(String msg) {
        super(msg + " does not fit the format of: <name or id>[:damage][#amount]");
    }
}
