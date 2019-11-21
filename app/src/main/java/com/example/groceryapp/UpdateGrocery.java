package com.example.groceryapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class UpdateGrocery extends AppCompatActivity {

    EditText up_nm, up_quantity;
    Button up_btn;
    Context context = this;
    DatabaseHandler db;
    SQLiteDatabase sqLiteDatabase;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_grocery);
        up_nm = findViewById(R.id.up_name);
        up_quantity = findViewById(R.id.up_quantity);
        up_btn = findViewById(R.id.update_btn);

        setTitle("Update Grocery Item");

        Intent intent=getIntent();


        if (intent!=null){
            id = intent.getStringExtra("id");
            String nm = intent.getStringExtra("name");
            String qn = intent.getStringExtra("quantity");
            up_nm.setText(nm);
            up_quantity.setText(qn);
            Log.e("int", "onCreate: "+nm);
            Log.e("int", "onCreate: "+qn);

        }
        up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateItem();
            }
        });
    }

    public void updateItem(){

        String name = up_nm.getText().toString();
        String quantity = up_quantity.getText().toString();

        Grocery grocery = new Grocery(id,name,quantity);
        db = new DatabaseHandler(context);
        sqLiteDatabase = db.getWritableDatabase();

        db.updateGrocery(grocery);
        Toast.makeText(getBaseContext(), "Record Updated", Toast.LENGTH_LONG).show();
        Log.e("List", "Id: " + id + " Name: " + name + " Quantity: " + quantity);
        db.close();

        finish();

    }
}
