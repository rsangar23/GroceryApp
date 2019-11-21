package com.example.groceryapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText mb_txt, pwd_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Login();
    }

    public void Login(){

        button = findViewById(R.id.login_btn);
        mb_txt = findViewById(R.id.mb_txt);
        pwd_txt = findViewById(R.id.pwd_txt);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mb_txt.getText().toString().equals("9665090697") &&
                    pwd_txt.getText().toString().equals("3672")){
                    String MyPreferences = "MyPref";
                    SharedPreferences sharedPreferences = getSharedPreferences(MyPreferences, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Login", "1");
                    editor.apply();
                    startActivity(new Intent(MainActivity.this, GroceryList.class));
                    Toast.makeText(getApplicationContext(),"Logged In...!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Wrong Credentials...!", Toast.LENGTH_SHORT).show();
                    mb_txt.setError("Plz Check Your Mobile No.");
                    pwd_txt.setError("Incorrect Password Entered");
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}


