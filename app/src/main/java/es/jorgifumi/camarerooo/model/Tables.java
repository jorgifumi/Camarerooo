package es.jorgifumi.camarerooo.model;

import java.util.ArrayList;

/**
 * Created by jorgifumi on 14/04/16.
 */
public class Tables {
    private ArrayList<Table> mTables;

    public Tables() {
        mTables = new ArrayList<Table>();
    }

    public ArrayList<Table> getTables() {
        return mTables;
    }

    public void setTables(ArrayList<Table> tables) {
        mTables = tables;
    }

    public void addTable(Table table) {
        mTables.add(table);
    }

    public void addTable(int position, Table table) {
        mTables.add(position, table);
    }
}
