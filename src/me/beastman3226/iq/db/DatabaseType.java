/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.beastman3226.iq.db;

/**
 *
 * @author Nicholas
 */
public enum DatabaseType {
    SQLite("SQLite", 1),
    MySQL("MySQL", 2),
    NOT_SUPPORTED("not sqlite", -1);

    private String name;
    private int id;
    DatabaseType(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getType() {
        return name;
    }

    public int response() {
        return id;
    }
}
