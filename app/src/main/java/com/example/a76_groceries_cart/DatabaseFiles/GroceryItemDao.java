package com.example.a76_groceries_cart.DatabaseFiles;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.a76_groceries_cart.GroceryItem;

import java.util.List;

@Dao
public interface GroceryItemDao {

    @Insert
    void insert(GroceryItem groceryItem);

    @Query("SELECT * FROM grocery_item")
    List<GroceryItem> getAllItems();

    @Query("UPDATE grocery_item SET rate = :newRate WHERE id=:id")
    void updateRate(int id, int newRate);

    @Query("SELECT * FROM grocery_item WHERE id=:id")
    GroceryItem getItemById(int id);

    @Query("UPDATE grocery_item SET reviews=:reviews WHERE id=:id")
    void updateReviews(int id, String reviews);

    @Query("SELECT * FROM grocery_item WHERE name LIKE :text")
    List<GroceryItem> searchForItem(String text);

    @Query("SELECT DISTINCT category FROM grocery_item ")
    List<String> getCategories();

    @Query("SELECT * FROM grocery_item WHERE category = :category")
    List<GroceryItem> getItemByCategory(String category);

    @Query("UPDATE grocery_item SET popularityPoint =:points WHERE id=:id")
    void updatePopularityPoint(int id,int points);

    @Query("UPDATE grocery_item SET userPoint =:points WHERE id=:id")
    void updateUserPoint(int id,int points);
}
