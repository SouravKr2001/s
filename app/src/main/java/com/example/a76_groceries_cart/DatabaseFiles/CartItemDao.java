package com.example.a76_groceries_cart.DatabaseFiles;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.a76_groceries_cart.GroceryItem;

import java.util.List;

import retrofit2.http.DELETE;

@Dao
public interface CartItemDao {
    @Query("INSERT INTO cart_items(groceryItemId) VALUES(:id)")
    void insert(int id);

    @Query("SELECT grocery_item.id,grocery_item.name,grocery_item.description," +
            "grocery_item.imageUrl,grocery_item.category,grocery_item.price," +
            "grocery_item.availableAmount,grocery_item.rate,grocery_item.userPoint," +
            "grocery_item.popularityPoint,grocery_item.reviews FROM grocery_item  INNER JOIN cart_items ON cart_items.groceryItemId=grocery_item.id")
    List<GroceryItem> getAllCartItems();

    @Query("DELETE FROM cart_items WHERE groceryItemId=:id")
    void deleteItemById(int id);

    @Query("DELETE FROM cart_items")
    void clearCart();
}

