package com.example.groceryapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class GrocerySplash extends AppCompatActivity {
    String MyPreference = "MyPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_splash);

        SharedPreferences sharedPreferences = getSharedPreferences(MyPreference, MODE_PRIVATE);
        if(sharedPreferences.getString("Login", "0").equals("0"))
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(GrocerySplash.this, MainActivity.class));
                    finish();
                }
            }, 3000);
        }
        else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(GrocerySplash.this, GroceryList.class));
                    finish();
                }
            }, 3000);
        }


    }

}
