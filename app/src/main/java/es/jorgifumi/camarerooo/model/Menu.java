package es.jorgifumi.camarerooo.model;

import java.util.LinkedList;

/**
 * Created by jorgifumi on 14/04/16.
 */
public class Menu {

    private LinkedList<Dish> mDishes;

    public Menu(LinkedList<Dish> dishes) {
        mDishes = dishes;
    }

    public LinkedList<Dish> getDishes() {
        return mDishes;
    }

    public void setDishes(LinkedList<Dish> dishes) {
        mDishes = dishes;
    }
}
