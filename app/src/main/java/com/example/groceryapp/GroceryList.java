package com.example.groceryapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class GroceryList extends AppCompatActivity {
    RecyclerView grocery_list;
    ProgressBar progressBar;
    GroceryAdapter groceryAdapter;
    EditText search_et;
    ArrayList<Grocery> groceries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list_main);

        grocery_list = findViewById(R.id.recycler);
        progressBar = findViewById(R.id.prg_bar);
        search_et = findViewById(R.id.txt_search);

        grocery_list.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        grocery_list.setLayoutManager(layoutManager);

        search_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.equals(""))
                filterList(s);
                else
                    getList();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        setTitle("My Grocery List");
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GroceryList.this, AddGrocery.class));
            }
        });
    }

    private void filterList(CharSequence s) {
        ArrayList<Grocery> grc = new ArrayList<>();
        for (Grocery g:groceries
             ) {
            if(g.getName().toLowerCase().contains(s.toString().toLowerCase())){
                grc.add(g);
            }
        }
        groceryAdapter = new GroceryAdapter(grc,this);
        grocery_list.setAdapter(groceryAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);
        getList();
    }

    public void getList(){

        DatabaseHandler db = new DatabaseHandler (this);

//        db.addGrocery(new Grocery("1", "1 Kg", "Tomatos"));
//        db.addGrocery(new Grocery("2", "2 Kg", "Potatos"));
//        db.addGrocery(new Grocery("3", "20 Kg", "Onions"));

        progressBar.setVisibility(View.GONE);

        groceries = db.getAllGrocery();

        for (Grocery grocery: groceries) {
            String log = "Id: " + grocery.getId() + " Name: " + grocery.getName() + " Quantity:" + grocery.getQuantity();
            Log.e("List", "onCreate: "+ log );
        }

        groceryAdapter = new GroceryAdapter(groceries,this);
        grocery_list.setAdapter(groceryAdapter);
    }


}
