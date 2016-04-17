package es.jorgifumi.camarerooo.model;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by jorgifumi on 14/04/16.
 */
public class Table implements Serializable {
    private String mName;
    private int mNumDiners;
    private Menu mOrders;

    public Table(String name, int numDiners, Menu orders, Float bill) {
        mName = name;
        mNumDiners = numDiners;
        mOrders = orders;
    }

    public Table(String name, int numDiners) {
        mName = name;
        mNumDiners = numDiners;
        mOrders = new Menu();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getNumDiners() {
        return mNumDiners;
    }

    public void setNumDiners(int numDiners) {
        mNumDiners = numDiners;
    }

    public Menu getOrders() {
        return mOrders;
    }

    public void setOrders(Menu orders) {
        mOrders = orders;
    }

    @Override
    public String toString() {
        return getName();
    }
}
