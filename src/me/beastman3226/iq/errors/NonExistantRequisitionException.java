/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.beastman3226.iq.errors;

/**
 *
 * @author Nicholas
 */
public class NonExistantRequisitionException extends Exception {

    /**
     * Creates a new instance of
     * <code>NonExistantRequisitionException</code> without detail message.
     */
    public NonExistantRequisitionException() {
    }

    /**
     * Constructs an instance of
     * <code>NonExistantRequisitionException</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public NonExistantRequisitionException(String msg) {
        super(msg);
    }
}
