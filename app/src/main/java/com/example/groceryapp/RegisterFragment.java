package com.example.groceryapp;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.groceryapp.API.APIClient;
import com.example.groceryapp.API.APIInterface;
import com.example.groceryapp.API.UserResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    View view;
    EditText name_et, email_et, password_et, cpass_et;
    Button register;
    Context context;
    AlertDialog alertDialog;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_register, container, false);

        name_et = view.findViewById(R.id.et_name);
        email_et = view.findViewById(R.id.et_eml);
        password_et = view.findViewById(R.id.et_pswd);
        cpass_et = view.findViewById(R.id.et_cpassword);
        register = view.findViewById(R.id.btn_register);
        context = this.getActivity();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSignUp();

                name_et.setText("");
                email_et.setText("");
                password_et.setText("");
                cpass_et.setText("");
            }
        });

        return view;

    }

    private void userSignUp()
    {
        String name = name_et.getText().toString().trim();
        String email = email_et.getText().toString().trim();
        String password = password_et.getText().toString().trim();
        String cpass = cpass_et.getText().toString().trim();

        UserResult user = new UserResult(name, email, password, cpass);

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<UserResult> call = apiInterface.createUser(user.getName(),
                                                    user.getEmail(),
                                                    user.getPassword(),
                                                    user.getC_password());

        call.enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
//                alertDialog.dismiss();
//                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                if(response.isSuccessful()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setView(R.layout.my_dialog);

                    alertDialog = builder.create();
                    alertDialog.show();

                    final Handler handler = new Handler();
                    final Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            if(alertDialog.isShowing())
                            {
                                alertDialog.dismiss();
                            }
                        }
                    };
                    handler.postDelayed(runnable, 2000);
//                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {

                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
