package com.nichias.realm_db;

import android.content.Context;
import com.nichias.dummy_model.Ingredients;
import java.util.Arrays;
import java.util.List;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class RealmLibrary {

    private static RealmLibrary realmInstance = new RealmLibrary();

    public static RealmLibrary getInstance() {
        return realmInstance;
    }

    /*
     * @param itemId
     * @param item_key
     * @param item_name
     * @param item_image
     * @param item_price_per_unit
     * @param category_id
     * @param ingredient_name
     * @param vendor_key
     * @param addOrDelete // 1 - add
                         // 2 - delete
     * @param Price
     * @param order_total
     * @param ingredientsList
     * @param is_ingredient
     * @param General_request
     */
    public void insertItem(final Context context, final String itemId,
                           final String item_key,
                           final String item_name,
                           final String item_image,
                           final String item_type,
                           final String item_price_per_unit,
                           final String category_id,
                           final String ingredient_name,
                           final String vendor_key,
                           final String addOrDelete,
                           final String Price,
                           final String order_total,
                           final List<Ingredients> ingredientsList,
                           final String is_ingredient,
                           final String General_request, String itemQuantity, String vendorName, String parcel) {

        Realm realm = Realm.getDefaultInstance();

        Boolean updateIngredients = false;
        Boolean sameIngredients = false;
        Boolean isDelete = false;
        realm.beginTransaction();
        RealmResults<CartRealmModel> cartRealmModels = realm.where(CartRealmModel.class).equalTo("item_id", itemId).findAll();
        CartRealmModel cartRealmModel = null;
        if (cartRealmModels.size() == 0) {
            cartRealmModel = new CartRealmModel();
            Number maxId = realm.where(CartRealmModel.class).max("id");
            int nextId = (maxId == null) ? 1 : maxId.intValue() + 1;
            cartRealmModel.setId(nextId);
            cartRealmModel.setQTY(itemQuantity);
            cartRealmModel.setItem_id(itemId);
        } else {
            for (int itemCount = 0; itemCount < cartRealmModels.size(); itemCount++) {
                cartRealmModel = cartRealmModels.get(itemCount);
                if (cartRealmModel.getItem_id().equals(itemId)) {
                    if (is_ingredient.equals("1")) {
                        RealmList<IngredientsRealmModel> existIng = cartRealmModel.getIngredientsRealmModel();
                        String existingIng[] = new String[existIng.size()];
                        String currentIng[] = new String[ingredientsList.size()];

                        for (int count = 0; count < existIng.size(); count++) {
                            existingIng[count] = existIng.get(count).getIngredientsId();
                        }
                        for (int count = 0; count < ingredientsList.size(); count++) {
                            currentIng[count] = ingredientsList.get(count).getIngredientsId();
                        }
                        Arrays.sort(existingIng);
                        Arrays.sort(currentIng);
                        Boolean insert = (Arrays.equals(existingIng, currentIng));
                        if (!insert) {
                            sameIngredients = true;
                        } else {
                            if (cartRealmModel.getQTY().equals("1") && addOrDelete.equals("2")) {
                                cartRealmModel.deleteFromRealm();
                                isDelete = true;
                            } else {
                                String qty = addOrDelete.equals("1") ? String.valueOf(Integer.parseInt(cartRealmModel.getQTY()) + Integer.parseInt(itemQuantity)) : String.valueOf(Integer.parseInt(cartRealmModel.getQTY()) - 1);
                                cartRealmModel.setQTY(qty);
                                updateIngredients = true;
                                sameIngredients = false;
                            }
                            break;
                        }

                    } else {
                        if (cartRealmModel.getQTY().equals("1") && addOrDelete.equals("2")) {
                            cartRealmModel.deleteFromRealm();
                            isDelete = true;
                            break;
                        } else {
                            String qty = addOrDelete.equals("1") ? String.valueOf(Integer.parseInt(cartRealmModel.getQTY()) + 1) : String.valueOf(Integer.parseInt(cartRealmModel.getQTY()) - 1);
                            cartRealmModel.setQTY(qty);
                        }
                    }
                } else {
                    cartRealmModel = new CartRealmModel();
                    cartRealmModel.setQTY(itemQuantity);
                    cartRealmModel.setItem_id(itemId);
                    Number maxId = realm.where(CartRealmModel.class).max("id");
                    int nextId = (maxId == null) ? 1 : maxId.intValue() + 1;
                    cartRealmModel.setId(nextId);
                }
            }
        }
        if (!isDelete) {
            if (sameIngredients) {
                cartRealmModel = new CartRealmModel();
                cartRealmModel.setQTY(itemQuantity);
                cartRealmModel.setItem_id(itemId);
                Number maxId = realm.where(CartRealmModel.class).max("id");
                int nextId = (maxId == null) ? 1 : maxId.intValue() + 1;
                cartRealmModel.setId(nextId);
            }

            if (cartRealmModel != null) {
                cartRealmModel.setItem_key(item_key);
                cartRealmModel.setItem_name(item_name);
                cartRealmModel.setItem_image(item_image);
                cartRealmModel.setItem_type(item_type);
                cartRealmModel.setItem_price_per_unit(item_price_per_unit);
                cartRealmModel.setCategory_id(category_id);
                cartRealmModel.setIngredient_name(ingredient_name);
                cartRealmModel.setVendor_key(vendor_key);
                cartRealmModel.setVendorName(vendorName);

                cartRealmModel.setPrice(Price);
                cartRealmModel.setOrder_total(order_total);
                cartRealmModel.setGeneral_request(General_request);
                cartRealmModel.setIs_ingredient(is_ingredient);
                cartRealmModel.setIs_parcel(parcel);

                if (!updateIngredients) {
                    RealmList<IngredientsRealmModel> ingredientsRealmModels = cartRealmModel.getIngredientsRealmModel();
                    for (int count = 0; count < ingredientsList.size(); count++) {
                        Ingredients ingredient = ingredientsList.get(count);
                        IngredientsRealmModel ingredientsRealmModel = new IngredientsRealmModel();
                        ingredientsRealmModel.setIngredient_name(ingredient.getIngredient_name());
                        ingredientsRealmModel.setIngredientsId(ingredient.getIngredientsId());
                        ingredientsRealmModel.setIngredientsKey(ingredient.getIngredientsKey());
                        ingredientsRealmModel.setIngredientsQuantity(ingredient.getIngredientsQuantity());
                        ingredientsRealmModel.setPrice(ingredient.getPrice());
                        ingredientsRealmModel.setGroupKey(ingredient.getGroupKey());

                        ingredientsRealmModels.add(ingredientsRealmModel);
                    }
                    cartRealmModel.setIngredientsRealmModel(ingredientsRealmModels);
                }

                realm.copyToRealmOrUpdate(cartRealmModel);
            } else {
                  // MyApplication.displayUnKnownError();
            }
        }
        realm.commitTransaction();
    }

    public void deleteItem(final String id) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        CartRealmModel item = realm.where(CartRealmModel.class).equalTo("item_key", id).findFirst();
        item.deleteFromRealm();
        realm.commitTransaction();
    }

    public void clearCart() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    public int getCartListSize(String vendor_key) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<CartRealmModel> cartRealmModels = realm.where(CartRealmModel.class).equalTo("vendor_key", vendor_key).findAll();
        return cartRealmModels.size();
    }

    public RealmResults<CartRealmModel> getCartList(String vendor_key) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(CartRealmModel.class).equalTo("vendor_key", vendor_key).findAll();
    }

    public RealmResults<CartRealmModel> getCartList() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(CartRealmModel.class).findAll();
    }

    public int getAllCartListSize() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(CartRealmModel.class).findAll().size();
    }

    public int getItems(String item_key) {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<CartRealmModel> items = realm.where(CartRealmModel.class).equalTo("item_key", item_key).findAll();
        int qty = 0;
        for (int count = 0; count < items.size(); count++){
            qty = qty + Integer.parseInt(items.get(count).getQTY());
        }
        return qty;
    }

    public CartRealmModel getCartListOrder(String item_key) {
        Realm realm = Realm.getDefaultInstance();

        return realm.where(CartRealmModel.class).equalTo("item_key", item_key).findFirst();
    }

}