package com.example.groceryapp;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddGrocery extends AppCompatActivity {
    EditText id_txt, nm_txt, qn_txt;
    Context context = this;
    DatabaseHandler db;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grocery);
        id_txt = findViewById(R.id.gr_id);
        nm_txt = findViewById(R.id.gr_name);
        qn_txt = findViewById(R.id.gr_quantity);

        setTitle("Add Grocery Item");

    }

    public void addItem(View view)
    {
        String id = id_txt.getText().toString();
        String name = nm_txt.getText().toString();
        String quantity = qn_txt.getText().toString();

        Grocery grocery = new Grocery(id,name,quantity);
        db = new DatabaseHandler(context);
        sqLiteDatabase = db.getWritableDatabase();

        db.addGrocery(grocery,sqLiteDatabase);
        Toast.makeText(getBaseContext(), "Record Saved", Toast.LENGTH_LONG).show();
        Log.e("List", "Id: " + id + " Name: " + name + " Quantity: " + quantity);
        db.close();

        finish();

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
