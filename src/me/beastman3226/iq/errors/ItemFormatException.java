package me.beastman3226.iq.errors;

/**
 *
 * @author beastman3226
 */
public class ItemFormatException extends Exception {

    public static String message = "That is the incorrect format for items";
    public String input;

    public ItemFormatException(String input) {
        super(message);
        this.input = input + " is not a legal input for item converting!";
    }

    @Override
    public String getLocalizedMessage() {
        return this.input;
    }

}
