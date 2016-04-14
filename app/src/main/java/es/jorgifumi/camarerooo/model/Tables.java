package es.jorgifumi.camarerooo.model;

import java.util.LinkedList;

/**
 * Created by jorgifumi on 14/04/16.
 */
public class Tables {
    private LinkedList<Table> mTables;

    public Tables(LinkedList<Table> tables) {
        mTables = tables;
    }

    public LinkedList<Table> getTables() {
        return mTables;
    }

    public void setTables(LinkedList<Table> tables) {
        mTables = tables;
    }
}
