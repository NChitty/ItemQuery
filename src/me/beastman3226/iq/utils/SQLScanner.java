package me.beastman3226.iq.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.beastman3226.iq.Main;

/**
 *
 * @author beastman3226
 */
public class SQLScanner {

    public static String storeReq(File file, String player, double price, String items) throws FileNotFoundException {
        String returnThis = null;
        BufferedReader br = new BufferedReader(new FileReader(file));
        try {
            String line;
            while((line = br.readLine()) != null) {
                returnThis = returnThis.concat(line + "\n");
            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(SQLScanner.class.getName()).log(Level.SEVERE, null, ex);
        }

        return returnThis.replace("%p", player)
                .replace("%d", price + "")
                .replace("%i", items);
    }
    public static void executeQuery(File file) throws FileNotFoundException, SQLException {
        String toReturn = null;
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        try {
            while((line = br.readLine()) != null) {
                toReturn = toReturn.concat(line + "\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(SQLScanner.class.getName()).log(Level.SEVERE, null, ex);
        }
        Main.db.getConnection().createStatement().execute(toReturn);
    }
}
