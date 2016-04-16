package es.jorgifumi.camarerooo.model;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by jorgifumi on 14/04/16.
 */
public class Menu implements Serializable {

    private LinkedList<Dish> mDishes;

    public Menu() {
        mDishes = new LinkedList<Dish>();
    }

    public Menu(LinkedList<Dish> dishes) {
        mDishes = dishes;
    }

    public LinkedList<Dish> getDishes() {
        return mDishes;
    }

    public void setDishes(LinkedList<Dish> dishes) {
        mDishes = dishes;
    }

    public void addDish(Dish dish) {
        mDishes.add(dish);
    }
}
