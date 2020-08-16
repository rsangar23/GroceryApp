package com.example.groceryapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    View v;
    RecyclerView grocery_list;
    ProgressBar progressBar;
    GroceryAdapter groceryAdapter;
    EditText search_et;
    ArrayList<Grocery> groceries;
    Context context;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);

        grocery_list = v.findViewById(R.id.recycler);
        progressBar = v.findViewById(R.id.prg_bar);
        search_et = v.findViewById(R.id.txt_search);
        context = this.getActivity();

        grocery_list.setHasFixedSize(true);
        progressBar.setVisibility(View.GONE);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);

        grocery_list.setLayoutManager(layoutManager);

//        search_et.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(!s.equals(""))
//                    filterList(s);
//                else
//                    getList();
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

        return v;
    }

    private void filterList(CharSequence s) {
        ArrayList<Grocery> grc = new ArrayList<>();
        for (Grocery g:groceries
        ) {
            if(g.getName().toLowerCase().contains(s.toString().toLowerCase())){
                grc.add(g);
            }
        }
        groceryAdapter = new GroceryAdapter(grc, context);
        grocery_list.setAdapter(groceryAdapter);
    }

    public void getList(){

        DatabaseHandler db = new DatabaseHandler (getActivity());

//        db.addGrocery(new Grocery("1", "1 Kg", "Tomatos"));
//        db.addGrocery(new Grocery("2", "2 Kg", "Potatos"));
//        db.addGrocery(new Grocery("3", "20 Kg", "Onions"));

        progressBar.setVisibility(View.GONE);

        groceries = db.getAllGrocery();

        for (Grocery grocery: groceries) {
            String log = "Id: " + grocery.getId() + " Name: " + grocery.getName() + " Quantity:" + grocery.getQuantity();
            Log.e("List", "onCreate: "+ log );
        }

        groceryAdapter = new GroceryAdapter(groceries,context);
        grocery_list.setAdapter(groceryAdapter);
    }
}
