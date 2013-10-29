package me.beastman3226.iq.utils;

/**
 *
 * @author beastman3226
 */
public class RequisitionMath {

    private static boolean isDivisible(int dividend) {
        return (dividend % 9 == 0);
    }

    public static int slots(int items) {
        while(!isDivisible(items)) {
            items++;
        }
        return items;
    }

}
