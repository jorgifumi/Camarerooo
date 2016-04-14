package es.jorgifumi.camarerooo.model;

import java.util.LinkedList;

/**
 * Created by jorgifumi on 14/04/16.
 */
public class Tables {
    private LinkedList<Table> mTables;

    public Tables() {
        mTables = new LinkedList<Table>();

        mTables.add(new Table("Mesa 3", 4, new Menu(new LinkedList<Dish>()),0.00f));
        mTables.add(new Table("Mesa prueba", 5, new Menu(new LinkedList<Dish>()),0.00f));
    }

    public LinkedList<Table> getTables() {
        return mTables;
    }

    public void setTables(LinkedList<Table> tables) {
        mTables = tables;
    }
}
