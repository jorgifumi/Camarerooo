package es.jorgifumi.camarerooo.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by jorgifumi on 14/04/16.
 */
public class Menu implements Serializable {

    private ArrayList<Dish> mDishes;

    public Menu() {
        mDishes = new ArrayList<Dish>();
    }

    public Menu(ArrayList<Dish> dishes) {
        mDishes = dishes;
    }

    public ArrayList<Dish> getDishes() {
        return mDishes;
    }

    public void setDishes(ArrayList<Dish> dishes) {
        mDishes = dishes;
    }

    public void addDish(Dish dish) {
        mDishes.add(dish);
    }

    public float getTotal() {
        float total = 0;

        for (Dish dish:this.getDishes()) {
            total += dish.getPrice();
        }
        return total;
    }
}
