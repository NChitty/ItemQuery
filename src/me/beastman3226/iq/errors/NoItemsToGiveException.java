/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.beastman3226.iq.errors;

/**
 *
 * @author Nicholas
 */
public class NoItemsToGiveException extends Exception {

    /**
     * Creates a new instance of
     * <code>NoItemsToGiveException</code> without detail message.
     */
    public NoItemsToGiveException() {
    }

    /**
     * Constructs an instance of
     * <code>NoItemsToGiveException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public NoItemsToGiveException(String msg) {
        super(msg);
    }
}
