package com.example.symatiqueapplication.Repository;

import android.content.Context;
import android.util.Log;

import com.example.symatiqueapplication.database.DatabaseHelper;
import com.example.symatiqueapplication.models.CartItem;
import com.example.symatiqueapplication.models.Product;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartRepository {

    private Dao<CartItem, Integer> cartItemDao;

    public CartRepository(Context context) {
        DatabaseHelper databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);

        try {
            cartItemDao = databaseHelper.getDao(CartItem.class);
        } catch (SQLException e) {
            Log.e("CartRepository", "Error getting DAO for CartItem", e);
        }
    }

    public void addProductToCart(Product product) {
        CartItem cartItem = new CartItem(product);
        try {
            cartItemDao.create(cartItem);
        } catch (SQLException e) {
            Log.e("CartRepository", "Could not add product to cart", e);
        }
    }

    public void deleteAllItems() {
        try {
            for (CartItem item : cartItemDao.queryForAll()) {
                cartItemDao.delete(item);
            }
        } catch (SQLException e) {
            Log.e("CartRepository", "Could not delete all items", e);
        }
    }
    public boolean isProductInCart(String productName) {
        try {
            // Check if the product with the given name is already in the cart
            List<CartItem> cartItems = cartItemDao.queryForEq("productName", productName);
            return !cartItems.isEmpty();
        } catch (SQLException e) {
            Log.e("CartRepository", "Error checking if product is in cart", e);
            return false;
        }
    }



    public int getItemCount() {
        try {
            return (int) cartItemDao.countOf();
        } catch (SQLException e) {
            Log.e("CartRepository", "Could not get item count", e);
            return 0;
        }
    }
    public List<CartItem> getAllCartItems() {
        try {
            return cartItemDao.queryForAll();
        } catch (SQLException e) {
            Log.e("CartRepository", "Could not retrieve cart items", e);
            return new ArrayList<>();
        }
    }

    // Autres méthodes au besoin pour la gestion des articles du panier
    // ...

    public void deleteItemById(int id){
        try{
            cartItemDao.deleteById(id);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void releaseHelper() {
        OpenHelperManager.releaseHelper();
    }
}
