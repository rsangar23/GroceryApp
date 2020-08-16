package com.example.groceryapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class GroceryList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    TextView name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.drawer_nav);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_navigation, R.string.close_navigation);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("My Grocery List");

        View header = navigationView.getHeaderView(0);
        name = header.findViewById(R.id.profile_username);
        email = header.findViewById(R.id.profile_email);

        SharedPreferences sp = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        name.setText(sp.getString("name", ""));
        email.setText(sp.getString("email", ""));

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(GroceryList.this, AddGrocery.class));
//            }
//        });

        displaySelectedScreen(R.id.nav_home);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        progressBar.setVisibility(View.VISIBLE);
//        progressBar.setIndeterminate(true);
//        getList();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        displaySelectedScreen(menuItem.getItemId());

        return true;
    }

    private void displaySelectedScreen(int itemId)
    {
        Fragment fragment = null;

        switch (itemId)
        {
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_profile:
//                fragment = new HomeFragment();
                break;
            case R.id.nav_setting:
//                fragment = new HomeFragment();
                break;
        }

        if(fragment != null)
        {
            FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
            tr.replace(R.id.content_frame, fragment);
            tr.commit();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
    }
}
