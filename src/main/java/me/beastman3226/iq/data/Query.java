package me.beastman3226.iq.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.beastman3226.iq.ItemQuery;

/**
 *
 * @author beastman3226
 */
public class Query {

    public static void addIndex(Data data) {
        try {
            Statement statement = ItemQuery.instance.getMySQLDatabase().getConnection().createStatement();
            String columns = "";
            String dataToInsert = "";
            int i = 0;
            for(String s : data.data.keySet()) {
                if(i != 0) {
                   columns = columns + ", " + s;
                   dataToInsert = dataToInsert + ", '" + data.data.get(s) + "'";
                } else {
                   columns = s;
                   dataToInsert = "'" + data.data.get(s) + "'";
                }
            }
            statement.execute("INSERT INTO 'requisitions'(" + columns +")" + "\n" +
                    "(" + dataToInsert + "}");
        } catch (SQLException ex) {
            ItemQuery.instance.getLogger().log(Level.SEVERE, ex.getMessage());
        }
    }

    public static void editIndex(Data data, String columnCheck, Object dataCheck) {
        try {
            Statement statement = ItemQuery.instance.getMySQLDatabase().getConnection().createStatement();
            String set = "";
            int i = 0;
            for(String s : data.data.keySet()) {
                if(i == 1) {
                    set = s + "='" + data.data.get(s) + "'";
                } else {
                    set = set + "," + s + "='" + data.data.get(s) + "'";
                }
            }
            statement.execute("UPDATE 'requisitions'" + "\n" +
                    "SET " + set + "\n" +
                    "WHERE " + columnCheck + "='" + dataCheck + "';");
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Object getInfo(String column, String columnCheck, Object data) {
        Object object = null;
        try {
            Statement statement = ItemQuery.instance.getMySQLDatabase().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT " + column + " FROM 'requisitions'" + "\n" +
                    "WHERE " + columnCheck +"='" + data + "';");
            while(rs.next()) {
                object = rs.getObject(column);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }

        return object;
    }

    public static class Data {
        public HashMap<String, Object> data = new HashMap<String, Object>();
        public Data addData(String column, Object data) {
            this.data.put(column, data);
            return this;
        }
    }

}
