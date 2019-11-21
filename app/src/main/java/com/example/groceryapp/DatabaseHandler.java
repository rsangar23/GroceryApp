package com.example.groceryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "groceryDetails";
    private static final String TABLE_GROCERY = "grocery";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_QUANTITY = "quantity";

    public DatabaseHandler(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GROCERY_TABLE = "CREATE TABLE " + TABLE_GROCERY + "(" +
        KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +KEY_NAME + " TEXT," + KEY_QUANTITY + " TEXT)";
        db.execSQL(CREATE_GROCERY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROCERY);

        onCreate(db);
    }

   void addGrocery(Grocery grocery, SQLiteDatabase db){

        ContentValues contentValues = new ContentValues();
        //contentValues.put(GroceryDetails.NewGroceryDetail.GR_ID, grocery.getId());
        contentValues.put(GroceryDetails.NewGroceryDetail.GR_NAME, grocery.getName());
        contentValues.put(GroceryDetails.NewGroceryDetail.GR_QUANTITY, grocery.getQuantity());

        db.insert(TABLE_GROCERY, null, contentValues);
        db.close();
    }

    Grocery getGrocery(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * from Grocery where id =" + id, null);
        if(cursor != null)
        cursor.moveToFirst();

        return new Grocery(cursor.getString(0), cursor.getString(1), cursor.getString(2));
    }

    public ArrayList<Grocery> getAllGrocery(){
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Grocery> groceries = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from Grocery order by id desc", null);
        if(cursor != null && cursor.moveToFirst())
        {
            do{
                groceries.add(new Grocery(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        return groceries;
    }

    public int updateGrocery(Grocery grocery){
        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, grocery.getName());
        contentValues.put(KEY_QUANTITY, grocery.getQuantity());

        return db.update(TABLE_GROCERY, contentValues, KEY_ID + "=" + grocery.getId(), null);
    }

    public void deleteGrocery(Grocery grocery){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GROCERY, KEY_ID + "=" + grocery.getId(), null);
        db.close();
    }

    public int getAllGroceryCount(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * from Grocery", null);
        if(cursor != null)
            return cursor.getCount();
        else
            return 0;
    }
}
