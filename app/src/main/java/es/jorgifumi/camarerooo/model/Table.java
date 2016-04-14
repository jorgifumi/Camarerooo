package es.jorgifumi.camarerooo.model;

import java.util.LinkedList;

/**
 * Created by jorgifumi on 14/04/16.
 */
public class Table {
    private String mName;
    private int mNumDiners;
    private Menu mOrders;
    private Float mBill;

    public Table(String name, int numDiners, Menu orders, Float bill) {
        mName = name;
        mNumDiners = numDiners;
        mOrders = orders;
        mBill = bill;
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

    public Float getBill() {
        return mBill;
    }

    public void setBill(Float bill) {
        mBill = bill;
    }
}
