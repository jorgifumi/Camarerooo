package es.jorgifumi.camarerooo.model;

import java.util.Set;

/**
 * Created by jorgifumi on 13/04/16.
 */
public class Dish {
    private String mName;
    private String mDescription;
    private Float mPrice;
    private Set<Allergen> mAllergens;
    private String mImageURL;
    private String mNotes;


    public Dish(String name, String description, Float price, Set<Allergen> allergens, String imageURL, String notes) {
        mName = name;
        mDescription = description;
        mPrice = price;
        mAllergens = allergens;
        mImageURL = imageURL;
        mNotes = notes;
    }

    public Dish(String name, String description, Float price, Set<Allergen> allergens, String imageURL) {
        mName = name;
        mDescription = description;
        mPrice = price;
        mAllergens = allergens;
        mImageURL = imageURL;
        mNotes = "";
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Set<Allergen> getAllergens() {
        return mAllergens;
    }

    public void setAllergens(Set<Allergen> allergens) {
        mAllergens = allergens;
    }

    public String getImageURL() {
        return mImageURL;
    }

    public void setImageURL(String imageURL) {
        mImageURL = imageURL;
    }

    public String getNotes() {
        return mNotes;
    }

    public void setNotes(String notes) {
        mNotes = notes;
    }
}