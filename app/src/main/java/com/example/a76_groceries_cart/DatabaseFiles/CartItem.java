package com.example.a76_groceries_cart.DatabaseFiles;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart_items")
public class CartItem {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int groceryItemId;

    public CartItem(int groceryItemId) {
        this.groceryItemId = groceryItemId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getGroceryItemId() {
        return groceryItemId;
    }
}
