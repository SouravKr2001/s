package com.example.a76_groceries_cart.DatabaseFiles;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.a76_groceries_cart.GroceryItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

@Database(entities = {GroceryItem.class,CartItem.class},version = 1)
public abstract class ShopDatabase extends RoomDatabase {
    public abstract GroceryItemDao groceryItemDao();
    public abstract CartItemDao cartItemDao();

    private static ShopDatabase instance;

    public static synchronized ShopDatabase getInstance(Context context){
        if (null == instance){
            instance= Room.databaseBuilder(context,ShopDatabase.class,"shop_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(initialCallback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback initialCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new InitialAsyncTask(instance).execute();
        }
    };

    private static class InitialAsyncTask extends AsyncTask<Void,Void,Void>{

        private GroceryItemDao groceryItemDao;


        public InitialAsyncTask(ShopDatabase db){
            this.groceryItemDao = db.groceryItemDao();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            ArrayList<GroceryItem> allItems=new ArrayList<>();
            GroceryItem milk =new GroceryItem("Milk","Milk is good for Health",
                    "http://pngimg.com/uploads/milk/small/milk_PNG12734.png","drink",2.3,0);
            allItems.add(milk);

            GroceryItem iceCream =new GroceryItem("Ice Cream","Icecreammmmmmmmmmmmmmmmmmm",
                    "https://freepngimg.com/thumb/ice_cream/9-2-ice-cream-png-picture.png","food",5.4,10);
            allItems.add(iceCream);


            GroceryItem soda =new GroceryItem("Soda","Soada..............",
                    "http://www.pngmart.com/files/16/Can-Soda-Transparent-PNG.png","drink",0.99,15);
            allItems.add(soda);

            for (GroceryItem g:allItems){
                groceryItemDao.insert(g);
            }
            return null;
        }
    }
}
