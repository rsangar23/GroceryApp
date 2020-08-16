package com.example.groceryapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.groceryapp.API.APIClient;
import com.example.groceryapp.API.APIInterface;
import com.example.groceryapp.API.LoginResult;
import com.example.groceryapp.API.UserResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    View view;
    EditText email_et, password_et;
    Button login_btn;
    Context context;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);

        email_et = view.findViewById(R.id.et_email);
        password_et = view.findViewById(R.id.et_password);
        login_btn = view.findViewById(R.id.btn_login);
        context = this.getActivity();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();

                email_et.setText("");
                password_et.setText("");
            }
        });

        return view;
    }

    private void loginUser()
    {
        String email = email_et.getText().toString().trim();
        String password = password_et.getText().toString().trim();

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<LoginResult> userCall = apiInterface.loginUser(email, password);

        userCall.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if(response.isSuccessful()){
//                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), GroceryList.class));
                    LoginResult loginResult = response.body();

                    SharedPreferences sp = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("name", loginResult.getUser().getName());
                    editor.putString("email", loginResult.getUser().getEmail());
                    editor.putString("token", loginResult.getUser().getAccessToken());
                    editor.apply();
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
