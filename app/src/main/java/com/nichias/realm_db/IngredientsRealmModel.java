package com.nichias.realm_db;

import io.realm.RealmObject;

public class IngredientsRealmModel extends RealmObject {

    private String ingredientsId;
    private String ingredientsKey;
    private String ingredient_name;
    private String ingredientsQuantity;
    private String price;
    private String groupKey;

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public String getIngredientsKey() {
        return ingredientsKey;
    }

    void setIngredientsKey(String ingredientsKey) {
        this.ingredientsKey = ingredientsKey;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    public String getIngredientsId() {
        return ingredientsId;
    }

    void setIngredientsId(String ingredientsId) {
        this.ingredientsId = ingredientsId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIngredientsQuantity() {
        return ingredientsQuantity;
    }


    void setIngredientsQuantity(String ingredientsQuantity) {
        this.ingredientsQuantity = ingredientsQuantity;
    }

}